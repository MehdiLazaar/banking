package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.ActionBourse;

import java.util.List;

public interface ActionBourseRepository {
    List<ActionBourse> findAll();
    ActionBourse save(ActionBourse action);
    List<ActionBourse> findAllByUser(String userId);
}
