package org.appynov.banking.domain.model;

import jakarta.validation.constraints.NotBlank;
import com.github.f4b6a3.ulid.UlidCreator;

public record Account(
        @NotBlank(message = "L'identifiant de compte ne peut pas être vide")
        String id,
        @NotBlank(message = "L'identifiant du client ne peut pas être vide")
        String client_id,
        @NotBlank(message = "Le solde peut etre negatif")
        Double balance,
        @NotBlank(message = "Le type de compte ne peut pas être vide")
        AccountType type,
        @NotBlank(message = "Le nom du compte ne peut pas être vide")
        String nom
) {
    public Account(String client_id, double balance, AccountType type, String nom) {
        this(UlidCreator.getUlid().toString(), client_id, balance, type, nom);
    }

    public enum AccountType {
        COURANT,
        LIVRET_A,
        PEA,
        PEL
    }
}
