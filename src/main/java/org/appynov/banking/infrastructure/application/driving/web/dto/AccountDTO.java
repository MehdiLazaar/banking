package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Account;

public record AccountDTO(
        String id,
        String client_id,
        double balance,
        String type,
        String nom
) {
    public static Account fromDTO(Account account) {
        return new Account(
                account.id(),
                account.client_id(),
                account.balance(),
                account.type(),
                account.nom()
        );
    }
    public static AccountDTO toDTO(Account account) {
        return new AccountDTO(
                account.id(),
                account.client_id(),
                account.balance(),
                account.type().name(),
                account.nom()
        );
    }
}
