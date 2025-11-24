package org.appynov.banking.infrastructure.driven.inmemory;

import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class InMemoryClientRepository implements ClientRepository {
    ConcurrentHashMap<String, Client> clients = new ConcurrentHashMap<>();

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clients.values());
    }
    @Override
    public boolean existsBy(String firstName, String lastName) {
        return clients.values().stream()
                .anyMatch(client -> client.getFirstName().equals(firstName)
                        &&
                        client.getLastName().equals(lastName));
    }
    @Override
    public Client save(Client client){
        if (client.getId() == null) client.setId(UUID.randomUUID().toString());
        clients.put(client.getId(), client);
        return client;
    }
}
