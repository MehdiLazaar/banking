package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Account;

public record CreateAccountRequest(
        String client_id,
        Double balance,
        String type,
        String nom
) {
    public Account toDomain() {
        return new Account(
                client_id,
                balance,
                Account.AccountType.valueOf(type),
                nom
        );
    }
}
