package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.ActionBourse;

import java.time.Instant;

public record ActionDTO(
        String id,
        String symbol,
        String nom,
        double prix,
        Instant lastUpdated
) {
    public static ActionDTO toDTO(ActionBourse action) {
        return new ActionDTO(
                action.id(),
                action.symbol(),
                action.nom(),
                action.prix(),
                action.lastUpdated()
        );
    }
}
