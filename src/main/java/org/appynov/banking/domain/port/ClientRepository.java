package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();
    Client save(Client client);
    boolean existsBy(String firstName, String lastName);
}
