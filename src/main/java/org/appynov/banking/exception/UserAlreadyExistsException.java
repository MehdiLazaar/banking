package org.appynov.banking.exception;

import jakarta.validation.constraints.NotBlank;

public class UserAlreadyExistsException extends Exception {
    @NotBlank(message = "L'identifiant utilisateur ne peut pas être vide")
    String id;
    @NotBlank(message = "Idf de l'utilisateur ne peuvent pas être vides")
    String s;
    public UserAlreadyExistsException(String id, String s) {
        super(String.format("L'utilisateur avec l'id %s et le clientId %s existe déjà.", id, s));
        this.id = id;
        this.s = s;
    }
}
