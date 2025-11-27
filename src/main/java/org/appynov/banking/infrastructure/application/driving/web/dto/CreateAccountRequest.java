package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Account;

public record CreateAccountRequest(
        String client_id,
        Double balance,
        String type,
        String nom
) {
    public Account toDomain() {
        // Vérifie que le type correspond à l'enum
        Account.AccountType accountType;
        try {
            accountType = Account.AccountType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Type de compte invalide : " + type);
        }
        return new Account(
                client_id,
                balance,
                Account.AccountType.valueOf(type),
                nom
        );
    }
}
