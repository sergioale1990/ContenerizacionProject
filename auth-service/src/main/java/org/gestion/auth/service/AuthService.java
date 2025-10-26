package org.gestion.auth.service;


import lombok.RequiredArgsConstructor;
import org.gestion.auth.dto.AuthRequest;
import org.gestion.auth.dto.AuthResponse;
import org.gestion.auth.dto.UsuarioRequest;
import org.gestion.auth.model.Access;
import org.gestion.auth.model.User;
import org.gestion.auth.repository.AccessRepository;
import org.gestion.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccessRepository accessRepository;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public User createUser(UsuarioRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Set<Access> accesses = request.getAccesses().stream()
                .map(accessName -> accessRepository.findByAccessName(accessName)
                .orElseThrow(() -> new RuntimeException("Permiso no Encontrado" + accessName)))
                .collect(Collectors.toSet());

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setAccess(accesses);
        return userRepository.save(newUser);
    }
}
