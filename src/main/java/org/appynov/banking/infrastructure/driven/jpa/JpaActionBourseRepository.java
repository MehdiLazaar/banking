package org.appynov.banking.infrastructure.driven.jpa;

import org.appynov.banking.domain.model.ActionBourse;
import org.appynov.banking.domain.port.ActionBourseRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class JpaActionBourseRepository implements ActionBourseRepository {
    private final JPActionBourseSpringRepository springRepo;

    public JpaActionBourseRepository(JPActionBourseSpringRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public ActionBourse save(ActionBourse action) {
        ActionEntity entity = new ActionEntity(
                action.id(),
                action.clientId(),
                action.symbol(),
                action.nom(),
                action.prix(),
                action.lastUpdated() != null ? action.lastUpdated() : Instant.now()
        );

        ActionEntity saved = springRepo.save(entity);

        return new ActionBourse(
                saved.getId(),
                saved.getClientId(),
                saved.getSymbol(),
                saved.getNom(),
                saved.getPrix(),
                saved.getLastUpdated()
        );
    }

    @Override
    public List<ActionBourse> findAllByUser(String userId) {
        return springRepo.findByClientId(userId)
                .stream()
                .map(e -> new ActionBourse(
                        e.getId(),
                        e.getClientId(),
                        e.getSymbol(),
                        e.getNom(),
                        e.getPrix(),
                        e.getLastUpdated()
                ))
                .toList();
    }

    @Override
    public List<ActionBourse> findAll() {
        return springRepo.findAll()
                .stream()
                .map(e -> new ActionBourse(
                        e.getId(),
                        e.getClientId(),
                        e.getSymbol(),
                        e.getNom(),
                        e.getPrix(),
                        e.getLastUpdated()
                ))
                .toList();
    }
}