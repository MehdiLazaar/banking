package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.Account;
import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.AccountRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListAccounts {
    private final AccountRepository accountRepository;

    public ListAccounts(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts(String clientId) {
        List<Account> comptes = accountRepository.getAccounts();
        if (comptes == null) return Collections.emptyList();

        if (clientId == null || clientId.isBlank()) {
            return comptes;
        }
        // filtre
        return comptes.stream()
                .filter(account -> clientId.equals(account.client_id()))
                .collect(Collectors.toList());
    }
}
