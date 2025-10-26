package org.gestion.clients.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.clients.model.Client;
import org.gestion.clients.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("hasAuthority('access.clients')")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('access.clients')")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('access.clients')")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('access.clients')")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

}
