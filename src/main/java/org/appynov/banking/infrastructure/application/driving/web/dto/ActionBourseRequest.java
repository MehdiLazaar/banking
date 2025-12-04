package org.appynov.banking.infrastructure.application.driving.web.dto;

public record ActionBourseRequest(
        String clientId,
        String symbol,
        String nom,
        double prix
) {
}
