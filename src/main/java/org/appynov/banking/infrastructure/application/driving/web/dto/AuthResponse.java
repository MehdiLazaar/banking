package org.appynov.banking.infrastructure.application.driving.web.dto;

public record AuthResponse(String token, String login, String clientId) {
}