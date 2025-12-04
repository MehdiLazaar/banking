package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    // Ajoute un nouvel utilisateur au dépôt
    User add(User user);
    // Vérifie si un utilisateur existe en fonction de son nom d'utilisateur (username),
    boolean existsByLoginIgnoreCase(String username);
    // Recherche un utilisateur par son nom d'utilisateur
    Optional<User> findByLoginIgnoreCase(String username);
}
