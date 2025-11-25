package org.appynov.banking.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String id) {
        super("Client avec id " + id + " non trouvé");
    }
}