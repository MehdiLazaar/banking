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
        Objects.requireNonNull(client, "client doit etre non null");
        // prévention de doublon
        if (client.getFirstName() != null && client.getLastName() != null) {
            boolean exists = clientRepository.existsBy(client.getFirstName(), client.getLastName());
            if (exists) {
                throw new ClientAlreadyExistsException(client.getFirstName(),  client.getLastName() + "Client existe déjà");
            }
        }
        return clientRepository.save(client);
    }
}
