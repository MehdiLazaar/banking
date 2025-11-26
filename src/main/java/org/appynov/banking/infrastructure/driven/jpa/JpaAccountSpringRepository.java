package org.appynov.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaAccountSpringRepository extends JpaRepository<AccountEntity, String> {

    // Trouve par b
    Optional<AccountEntity> findById(String id);
    List<AccountEntity> findByClientId(String clientId);

    boolean existsById(String id);
    boolean existsByClientId(String clientId);

    Optional<AccountEntity> findByIdAndClientId(String id, String clientId);
    Optional<AccountEntity> findByIdAndBalance(String id, Double balance);
    Optional<AccountEntity> findByBalance(Double balance);
    Optional<AccountEntity> findByBalanceAndClientId(Double balance, String clientId);

    List<AccountEntity> findByNomContaining(String nom);
    List<AccountEntity> findByNomContainingAndBalance(String nom, Double balance);
    List<AccountEntity> findByNomContainingAndBalanceAndClientId(String nom, Double balance, String clientId);


    long countById(String id);
    long countByClientId(String clientId);
}
