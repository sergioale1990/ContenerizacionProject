package org.gestion.auth.repository;

import org.gestion.auth.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRepository extends JpaRepository<Access, Long> {
    Optional<Access> findByAccessName(String accessName);
}
