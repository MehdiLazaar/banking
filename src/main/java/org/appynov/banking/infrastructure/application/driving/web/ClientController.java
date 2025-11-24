package org.appynov.banking.infrastructure.application.driving.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.ClientRepository;
import org.appynov.banking.domain.usecase.CreateClient;
import org.appynov.banking.domain.usecase.ListClients;
import org.appynov.banking.infrastructure.application.driving.web.dto.ClientDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.CreateClientRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {
    private final ListClients listClients;
    private final CreateClient createClient;

    public ClientController(ClientRepository clientRepository) {
        this.listClients = new ListClients(clientRepository);
        this.createClient = new CreateClient(clientRepository);
    }
    @GetMapping("/clients")
    @Operation(summary = "Liste tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients")
    public List<ClientDTO> getClients() {
        return listClients.all()
                .stream()
                .map(client -> new ClientDTO(
                        client.getId(),
                        client.getFirstName(),
                        client.getLastName()
                ))
                .collect(Collectors.toList());
    }
    @PostMapping("/clients")
    @Operation(summary = "Créer un client")
    @ApiResponse(responseCode = "201", description = "Client créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Client existe déjà")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody CreateClientRequest request) {
        Client client = new Client(request.lastName(), request.firstName());
        Client created = createClient.execute(client);
        ClientDTO response = new ClientDTO(
                created.getId(),
                created.getFirstName(),
                created.getLastName()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
