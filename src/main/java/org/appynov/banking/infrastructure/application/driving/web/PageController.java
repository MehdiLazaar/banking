package org.appynov.banking.infrastructure.application.driving.web;


import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.domain.usecase.ListClients;
import org.appynov.banking.infrastructure.application.driving.web.dto.AccountDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.ClientDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class PageController {
    private final ListClients listClients;

    public PageController(ListClients listClients) {
        this.listClients = listClients;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<ClientDTO> clients = listClients.findAll()
                .stream()
                .map(ClientDTO::fromDomain)
                .toList();

        model.addAttribute("clients", clients);

        return "accueil";
    }

    @GetMapping("/clients/{id}")
    public String showClientAccounts(@PathVariable String id, Model model) {
        var client = listClients.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (client == null) {
            model.addAttribute("errorMessage", "Client introuvable !");
            return "error";
        }

        // Récupérer les comptes associés
        List accounts = ListAccounts.getAccounts(id)
                .stream()
                .map(AccountDTO::fromDomain)
                .toList();

        model.addAttribute("client", ClientDTO.fromDomain(client));
        model.addAttribute("accounts", accounts);

        return "accounts";
    }
}
