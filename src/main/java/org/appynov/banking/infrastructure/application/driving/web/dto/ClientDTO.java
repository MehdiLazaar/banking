package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Client;

public record ClientDTO(
        String id,
        String firstName,
        String lastName
) {
    public static ClientDTO fromDomain(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName()
        );
    }
}
