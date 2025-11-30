package org.appynov.banking.infrastructure.application.driving.web;

import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.usecase.CreateAccount;
import org.appynov.banking.domain.usecase.CreateClient;
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

import java.util.List;
import java.util.Optional;

@Controller
public class PageController {
    private final CreateAccount createAccount;
    private final ListAccounts listAccounts;
    private final ListClients listClients;
    private final CreateClient createClient;

    public PageController(ListAccounts listAccounts, ListClients listClients,
                          CreateAccount createAccount, CreateClient createClient) {
        this.createAccount = createAccount;
        this.listAccounts = listAccounts;
        this.listClients = listClients;
        this.createClient = createClient;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


    @GetMapping("/index")
    public String index(Model model) {
        List<ClientDTO> clients = listClients.findAll()
                .stream()
                .map(ClientDTO::toDTO)
                .toList();
        model.addAttribute("clients", clients);
        return "index";
    }

    @PostMapping("/clients/add")
    public String addClient(@RequestParam String firstName,
                            @RequestParam String lastName) {
        createClient.execute(new Client(lastName, firstName));
        return "redirect:/index";
    }

    @GetMapping("/clients/{id}/compteClient")
    public String showClientAccounts(@PathVariable String id, Model model) {

        Optional<Client> client = listClients.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        if (client.isEmpty()) {
            return "redirect:/index";
        }

        List<AccountDTO> accounts = listAccounts.getAccounts(id).stream()
                .map(AccountDTO::toDTO)
                .toList();

        model.addAttribute("client", ClientDTO.toDTO(client.get()));
        model.addAttribute("accounts", accounts);

        return "accounts";
    }

    @PostMapping("/clients/{id}/compteClient")
    public String createAccountForClient(@PathVariable String id,
                                         @RequestParam String type,
                                         @RequestParam String nom,
                                         @RequestParam double balance) {
        CreateAccountRequest request = new CreateAccountRequest(id, balance, type, nom);
        createAccount.createAccount(request.toDomain());

        return "redirect:/clients/" + id + "/compteClient";
    }
}