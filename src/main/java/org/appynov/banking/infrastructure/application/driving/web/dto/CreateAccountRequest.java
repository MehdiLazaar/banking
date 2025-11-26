package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Account;

public record CreateAccountRequest(
        String clientId,
        Double balance,
        String type,
        String nom
) {
    public Account toDomain() {
        return new Account(
                clientId,
                balance,
                Account.AccountType.valueOf(type),
                nom
        );
    }
}
