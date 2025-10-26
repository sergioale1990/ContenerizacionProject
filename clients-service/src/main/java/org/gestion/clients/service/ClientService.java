package org.gestion.clients.service;


import lombok.RequiredArgsConstructor;
import org.gestion.clients.model.Client;
import org.gestion.clients.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        if (clientRepository.findByCiNit(client.getCiNit()).isPresent()) {
            throw new RuntimeException("Numero de identificacion ya registrado en otro cliente");
        }
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
