package org.appynov.banking.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String firstName, String lastName) {
        super("Le client " + firstName + " " + lastName + " existe déjà");
    }
}
