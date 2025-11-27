package org.appynov.banking.infrastructure.application.driving.web.dto;

import org.appynov.banking.domain.model.Client;

public record ClientDTO(
        String id,
        String firstName,
        String lastName
) {
    public static ClientDTO fromDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName()
        );
    }

    // Convertit un ClientDTO en ClientDTO (utile si tu veux conserver le pattern)
    public static Client toDTO(ClientDTO clientDTO) {
        return new Client(
                clientDTO.id(),
                clientDTO.firstName(),
                clientDTO.lastName()
        );
    }
}
