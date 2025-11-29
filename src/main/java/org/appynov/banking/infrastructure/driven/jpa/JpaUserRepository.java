package org.appynov.banking.infrastructure.driven.jpa;

import org.appynov.banking.domain.model.User;
import org.appynov.banking.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final JpaUserSpringRepository springRepository;

    public JpaUserRepository(JpaUserSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    // --- Mapping DOMAIN → ENTITY ---
    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.id(),
                user.username(),
                user.mdpHash(),
                user.clientId()
        );
    }

    // --- Mapping ENTITY → DOMAIN ---
    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getClientId()
        );
    }

    @Override
    public User add(User user) {
        UserEntity saved = springRepository.save(toEntity(user));
        return toDomain(saved);
    }

    @Override
    public boolean existsByLoginIgnoreCase(String username) {
        return springRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public Optional<User> findByLoginIgnoreCase(String username) {
        return springRepository
                .findByUsernameIgnoreCase(username)
                .map(this::toDomain);
    }
}
