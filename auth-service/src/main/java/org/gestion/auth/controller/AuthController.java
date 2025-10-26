package org.gestion.auth.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.auth.dto.AuthRequest;
import org.gestion.auth.dto.AuthResponse;
import org.gestion.auth.dto.UsuarioRequest;
import org.gestion.auth.model.User;
import org.gestion.auth.repository.UserRepository;
import org.gestion.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Acceso autorizado");
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('access.admin')")
    public ResponseEntity<User> createUser(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('access.admin')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
