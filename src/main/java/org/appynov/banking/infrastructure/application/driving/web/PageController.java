package org.appynov.banking.infrastructure.application.driving.web;

import org.appynov.banking.domain.usecase.CreateAccount;
import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.domain.usecase.ListClients;
import org.appynov.banking.infrastructure.application.driving.web.dto.AccountDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.ClientDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.CreateAccountRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    private final CreateAccount createAccount;
    private final ListAccounts listAccounts;
    private final ListClients listClients;

    public PageController(ListAccounts listAccounts, ListClients listClients, CreateAccount createAccount) {
        this.createAccount = createAccount;
        this.listAccounts = listAccounts;
        this.listClients = listClients;
    }

    // Page d'accueil : liste des clients
    @GetMapping("/index")
    public String index(Model model) {
        var clients = listClients.findAll()
                .stream()
                .map(ClientDTO::toDTO)
                .toList();
        model.addAttribute("clients", clients);
        return "index";
    }

    // Page comptes pour un client
    @GetMapping("/clients/{id}/view")
    public String showClientAccounts(@PathVariable String id, Model model) {

        var client = listClients.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (client.isEmpty()) {
            return "redirect:/index";
        }

        var accounts = listAccounts.getAccounts(id).stream()
                .map(AccountDTO::toDTO)
                .toList();

        model.addAttribute("client", ClientDTO.toDTO(client.get()));
        model.addAttribute("accounts", accounts);

        return "accounts";
    }




    // Route POST pour créer un compte depuis le formulaire Thymeleaf
    @PostMapping("/clients/{id}/create-account")
    public String createAccountForClient(@PathVariable String id,
                                         @RequestParam String type,
                                         @RequestParam String nom,
                                         @RequestParam double balance) {
        var request = new CreateAccountRequest(id, balance, type, nom);
        createAccount.createAccount(request.toDomain());

        return "redirect:/clients/" + id + "/view";
    }
}