package org.appynov.banking.infrastructure.application.driving.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String username, @NotBlank String lastName, @NotBlank String firstName,
                              @NotBlank String password) {
}
