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
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final UserRepository userRepository;
    private final AccessRepository accessRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        try {
            List<String> accessNames = List.of("access.sales", "access.products", "access.clients", "access.logs");
            Set<Access> accesses = new HashSet<>();

            for (String accessName : accessNames) {
                Access access = accessRepository.findByAccessName(accessName)
                        .orElseGet(() -> accessRepository.save(new Access(null, accessName)));
                accesses.add(access);
            }

            userRepository.findByUsername("admin").ifPresentOrElse(
                user -> System.out.println("Usuario admin ya existe"),
                () -> {
                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setPassword(passwordEncoder.encode("admin123"));
                    admin.setAccess(accesses);
                    userRepository.save(admin);
                    System.out.println("Se ha creado el usuario administrador por defecto.");
                }
            );

        } catch (Exception e) {
            System.err.println("Error al inicializar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

