package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User add(User user);
    boolean existsByLoginIgnoreCase(String username);
    Optional<User> findByLoginIgnoreCase(String username);
}
