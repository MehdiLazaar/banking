package org.appynov.banking.domain.usecase;

import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;
import org.appynov.banking.exception.ClientAlreadyExistsException;

import java.util.Objects;

public class CreateClient {
    private final ClientRepository clientRepository;

    public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Client client) {
        Objects.requireNonNull(client, "client doit être non null");

        // prévention de doublon
        if (clientRepository.existsBy(client.getFirstName(), client.getLastName())) {
            throw new ClientAlreadyExistsException(client.getFirstName(), client.getLastName());
        }

        return clientRepository.save(client);
    }
}
