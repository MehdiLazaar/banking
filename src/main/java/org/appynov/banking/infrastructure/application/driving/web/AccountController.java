package org.appynov.banking.infrastructure.application.driving.web;

import org.appynov.banking.domain.usecase.CreateAccount;
import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.infrastructure.application.driving.web.dto.AccountDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AccountController {
    private final CreateAccount createAccountUseCase;
    private final ListAccounts listAccountsUseCase;

    public AccountController(CreateAccount createAccountUseCase, ListAccounts listAccountsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.listAccountsUseCase = listAccountsUseCase;
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> create(@RequestBody CreateAccountRequest request) {
        var account = createAccountUseCase.createAccount(request.toDomain());
        return new ResponseEntity<>(AccountDTO.fromDomain(account), HttpStatus.CREATED);
    }

    @GetMapping("/accounts")
    public ResponseEntity<Iterable<AccountDTO>> list(@RequestParam(required = false) String clientId) {
        var accounts = listAccountsUseCase.getAccounts(clientId)
                .stream().map(AccountDTO::fromDomain)
                .toList();
        return ResponseEntity.ok(accounts);
    }
    // Gestion simple des exceptions
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
