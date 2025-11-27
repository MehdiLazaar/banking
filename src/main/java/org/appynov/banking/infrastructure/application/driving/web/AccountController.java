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
@RequestMapping("/api") // Toutes les routes REST passent par /api
public class AccountController {

    private final CreateAccount createAccountUseCase;
    private final ListAccounts listAccountsUseCase;
    private final ListClients listClients;

    public AccountController(CreateAccount createAccountUseCase,
                             ListAccounts listAccountsUseCase,
                             ListClients listClients) {
        this.createAccountUseCase = createAccountUseCase;
        this.listAccountsUseCase = listAccountsUseCase;
        this.listClients = listClients;
    }

    // Créer un compte
    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> create(@RequestBody CreateAccountRequest request) {
        var account = createAccountUseCase.createAccount(request.toDomain());
        return new ResponseEntity<>(AccountDTO.toDTO(account), HttpStatus.CREATED);
    }
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> list(@RequestParam String clientId) {
        List<AccountDTO> accounts = listAccountsUseCase.getAccounts(clientId)
                .stream()
                .map(AccountDTO::toDTO)
                .toList();
        return ResponseEntity.ok(accounts);
    }


    // Récupérer les infos d’un client
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String id) {
        Optional<ClientDTO> client = listClients.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .map(ClientDTO::toDTO)
                .findFirst();
        return client.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Gestion simple des exceptions
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
