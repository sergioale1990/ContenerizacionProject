package org.gestion.auth.config;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.gestion.auth.model.Access;
import org.gestion.auth.model.User;
import org.gestion.auth.repository.AccessRepository;
import org.gestion.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        List<String> accessNames = List.of("access.sales", "access.products", "access.clients", "access.logs");
        HashSet<Access> accesses = new HashSet<>();
        for (String accessName : accessNames) {
            Access access = accessRepository.findByAccessName(accessName)
                    .orElseGet(() -> accessRepository.save(new Access(null, accessName)));
            accesses.add(access);
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setAccess(accesses);
        userRepository.save(admin);
        System.out.println("Se ha creado el usuario administrador");
    }

}
