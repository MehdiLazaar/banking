package org.appynov.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPActionBourseSpringRepository extends JpaRepository<ActionEntity, String> {
    List<ActionEntity> findByClientId(String clientId);
}
