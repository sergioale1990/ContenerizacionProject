package org.gestion.clients.repository;

import org.gestion.clients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByName(String name);
    Optional<Client> findByCiNit(String ciNit);
}
