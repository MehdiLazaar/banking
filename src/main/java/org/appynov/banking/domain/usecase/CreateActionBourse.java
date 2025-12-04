package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.ActionBourse;
import org.appynov.banking.domain.port.AccountRepository;
import org.appynov.banking.domain.port.ActionBourseRepository;

public class CreateActionBourse {
    private final ActionBourseRepository repository;

    public CreateActionBourse(ActionBourseRepository repository) {
        this.repository = repository;
    }

    public ActionBourse execute(String clientId, String symbol, String nom, double prix) {
        ActionBourse action = new ActionBourse(clientId, symbol, nom, prix, java.time.Instant.now());
        return repository.save(action);
    }
}
