package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.Account;
import org.appynov.banking.domain.port.AccountRepository;

import java.util.Objects;

public class CreateAccount {
    private final AccountRepository accountRepository;

    public CreateAccount(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public boolean existsByAccount(String idCompte) {
        return accountRepository.existsByAccount(idCompte);
    }
    public Account createAccount(Account compte) {
        Objects.requireNonNull(compte, "compte == null");

        if (existsByAccount(compte.id())) {
            throw new IllegalArgumentException(
                    "Le compte avec le numéro " + compte.id() + " existe déjà."
            );
        }
        return accountRepository.creationAccount(compte);
    }

}
