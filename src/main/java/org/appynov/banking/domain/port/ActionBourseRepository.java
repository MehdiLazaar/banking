package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.ActionBourse;

import java.util.List;

public interface ActionBourseRepository {
    // On recupere la liste de toutes les actions
    List<ActionBourse> findAll();
    // On cree une action
    ActionBourse save(ActionBourse action);
    // On recupere les actions d un utilisateur
    List<ActionBourse> findAllByUser(String userId);
}
