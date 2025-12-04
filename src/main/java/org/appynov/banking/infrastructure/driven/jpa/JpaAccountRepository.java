package org.appynov.banking.infrastructure.driven.jpa;

import org.appynov.banking.domain.model.Account;
import org.appynov.banking.domain.port.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final JpaAccountSpringRepository springRepository;

    public JpaAccountRepository(JpaAccountSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public List<Account> getAccounts() {
        return springRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Account creationAccount(Account account) {
        AccountEntity entity = new AccountEntity(
                account.id(),
                account.client_id(),
                account.balance(),
                account.type().name(),
                account.nom()
        );
        AccountEntity saved = springRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public boolean existsByAccount(String idCompte) {
        return springRepository.existsById(idCompte);
    }

    private Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getClientId(),
                entity.getBalance(),
                Account.AccountType.valueOf(entity.getType()),
                entity.getNom()
        );
    }
}
