package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.model.User;
import org.appynov.banking.domain.port.UserRepository;
import org.appynov.banking.exception.UserAlreadyExistsException;

import java.util.Objects;

public class CreateUser {
    private final UserRepository userRepository;
    private final CreateClient createClient;

    public CreateUser(UserRepository userRepository, CreateClient createClient) {
        this.userRepository = userRepository;
        this.createClient = createClient;
    }

    public User create(String username, String password, String lastName, String firstName) {
        if (userRepository.existsByLoginIgnoreCase(username)) {
            throw new UserAlreadyExistsException(username);
        }
        Client c = new Client(lastName, firstName);
        var client = createClient.execute(c);
        return userRepository.add(new User(username, password, client.getId()));
    }

    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String username) {
            super("User already exists: " + username);
        }
    }
}