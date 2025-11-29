package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.User;
import org.appynov.banking.domain.port.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public class FindUser {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public FindUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User by(String username, String password) {
        var userOpt = userRepository.findByLoginIgnoreCase(username);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        var user = userOpt.get();
        if (!passwordEncoder.matches(password, user.mdpHash())) {
            throw new InvalidLoginException(username);
        }
        return user;
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String username) {
            super("User not found: " + username);
        }
    }

    public static class InvalidLoginException extends RuntimeException {
        public InvalidLoginException(String username) {
            super("Invalid login: " + username);
        }
    }
}
