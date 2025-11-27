package org.appynov.banking.infrastructure.application.driving.web;


import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.domain.usecase.ListClients;
import org.appynov.banking.infrastructure.application.driving.web.dto.AccountDTO;
import org.appynov.banking.infrastructure.application.driving.web.dto.ClientDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class PageController {
    private final ListClients listClients;
    private final ListAccounts listAccounts;

    public PageController(ListClients listClients, ListAccounts listAccounts) {
        this.listClients = listClients;
        this.listAccounts = listAccounts;
    }

    @GetMapping("/index")
    public String index(Model model) {
        // Récupérer tous les clients et les transformer en ClientDTO
        List<ClientDTO> clients = listClients.findAll()
                .stream()
                .map(ClientDTO::fromDTO) // fromDTO pour passer du domaine à la vue
                .toList();

        // Ajouter la liste des clients au modèle pour Thymeleaf
        model.addAttribute("clients", clients);

        return "index"; // Le template Thymeleaf à afficher
    }
}
