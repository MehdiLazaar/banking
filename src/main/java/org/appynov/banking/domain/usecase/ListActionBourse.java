package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.ActionBourse;
import org.appynov.banking.domain.port.ActionBourseRepository;

import java.util.List;

public class ListActionBourse {
    private final ActionBourseRepository repository;

    public ListActionBourse(ActionBourseRepository repository) {
        this.repository = repository;
    }

    public List<ActionBourse> findAll() {
        return repository.findAll();
    }

    public List<ActionBourse> findAllByUser(String userId) {
        return repository.findAllByUser(userId);
    }
}
