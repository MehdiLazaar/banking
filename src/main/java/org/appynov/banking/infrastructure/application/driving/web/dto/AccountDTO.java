package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Account;

public record AccountDTO(
        String id,
        String client_id,
        double type,
        String balance,
        String nom
) {
    public static AccountDTO fromDomain(Account account) {
        return new AccountDTO(
                account.id(),
                account.client_id(),
                account.balance(),
                account.type().name(),
                account.nom()
        );
    }
}
