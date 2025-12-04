package org.appynov.banking.infrastructure.application.driving.web;

import org.appynov.banking.domain.usecase.ListActionBourse;
import org.appynov.banking.infrastructure.application.driving.web.dto.ActionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/market")
public class MarketController {
    private final ListActionBourse listActions;
    public MarketController(ListActionBourse listActions) {
        this.listActions = listActions;
    }

    @GetMapping("/actions")
    public List<ActionDTO> getAllActions() {
        // Retourne toutes les actions, indépendamment du client
        return listActions.findAll()
                .stream()
                .map(ActionDTO::toDTO)
                .toList();
    }
}
