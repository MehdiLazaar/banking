package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;

import java.util.Objects;

public class CreateClient {
    private final ClientRepository clientRepository;

    public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Client client) {
        Objects.requireNonNull(client, "client doit etre non null");
        // prévention de doublon
        if (client.getFirstName() != null && client.getLastName() != null) {
            boolean exists = clientRepository.existsBy(client.getFirstName(), client.getLastName());
            if (exists) {
                throw new IllegalArgumentException("Client already exists");
            }
        }
        return clientRepository.save(client);
    }
}
