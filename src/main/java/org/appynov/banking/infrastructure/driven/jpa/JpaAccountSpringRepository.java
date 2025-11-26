package org.appynov.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAccountSpringRepository extends JpaRepository<AccountEntity, String> {

    // Trouve par b
    List<AccountEntity> findByB(String b);

    // Trouve par b et c
    Optional<AccountEntity> findByBAndC(String b, Integer c);

    // Trouve par b contenant une chaîne
    List<AccountEntity> findByBContaining(String pattern);

    // Compte les demos avec un b
    long countByB(String b);

    // Vérifie si un demo existe avec c
    boolean existsByC(Integer c);
}
