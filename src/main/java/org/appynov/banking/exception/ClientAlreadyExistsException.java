package org.appynov.banking.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    private final String conflis;
    public ClientAlreadyExistsException(String message, String conflis) {
        super(message);
        this.conflis = conflis;
    }
    public String getConflis() {
        return conflis;
    }
}
