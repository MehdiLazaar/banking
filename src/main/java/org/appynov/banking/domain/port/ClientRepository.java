package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.Client;

import java.util.List;

public interface ClientRepository {
    // On recupere la liste de tous les clients
    List<Client> findAll();
    // On cree un client
    Client save(Client client);
    // On verifie si un client existe deja par son nom et prenom
    boolean existsBy(String firstName, String lastName);
}
