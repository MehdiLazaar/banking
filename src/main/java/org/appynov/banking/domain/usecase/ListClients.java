package org.appynov.banking.domain.usecase;


import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListClients {
    private final ClientRepository clientRepository;
    public ListClients(ClientRepository clientRepository){
        if(clientRepository == null){
            throw new IllegalArgumentException("ClientRepository ne peut pas être null");
        }
        this.clientRepository = clientRepository;
    }
    public List<Client> all(){
        List<Client> clients = clientRepository.findAll();
        return clients == null ? Collections.emptyList() : clients;
    }
}