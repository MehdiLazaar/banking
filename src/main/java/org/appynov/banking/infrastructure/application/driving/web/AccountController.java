package org.appynov.banking.infrastructure.application.driving.web;

import org.appynov.banking.domain.usecase.CreateAccount;
import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.domain.usecase.ListClients;
import org.appynov.banking.infrastructure.application.driving.web.dto.AccountDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.ClientDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final CreateAccount createAccount;
    private final ListClients listClients;

    public AccountController(CreateAccount createAccount,
                             ListClients listClients) {
        this.createAccount = createAccount;
        this.listClients = listClients;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> create(@RequestBody CreateAccountRequest request) {
        try {
            var account = createAccount.createAccount(request.toDomain());
            return new ResponseEntity<>(AccountDTO.toDTO(account), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    // Récupérer les infos d un client
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String id) {
        Optional<ClientDTO> client = listClients.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .map(ClientDTO::toDTO)
                .findFirst();
        return client.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
