package org.gestion.auth.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioRequest {
    private String username;
    private String password;
    private Set<String> accesses;
}
