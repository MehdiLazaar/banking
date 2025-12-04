package org.appynov.banking.infrastructure.application.driving.web;

import io.jsonwebtoken.Jwt;
import org.appynov.banking.domain.model.ActionBourse;
import org.appynov.banking.domain.usecase.CreateActionBourse;
import org.appynov.banking.domain.usecase.ListActionBourse;
import org.appynov.banking.infrastructure.application.driving.web.dto.ActionBourseRequest;
import org.appynov.banking.infrastructure.application.driving.web.security.JwtAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class ActionBourseController {
    private final ListActionBourse listActionBourse;
    private final CreateActionBourse createActionBourse;

    public ActionBourseController(ListActionBourse listActionBourse, CreateActionBourse createActionBourse) {
        this.listActionBourse = listActionBourse;
        this.createActionBourse = createActionBourse;
    }

    @GetMapping("/actions")
    public ResponseEntity<List<ActionBourse>> getAll() {
        try {
            List<ActionBourse> actions = listActionBourse.findAll();
            return ResponseEntity.ok(actions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/actions")
    public ResponseEntity<ActionBourse> create(
            @RequestBody ActionBourseRequest request,
            @AuthenticationPrincipal JwtAuthenticationFilter.MyBankPrincipal principal) {

        if (!principal.clientId().equals(request.clientId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        try {
            var action = createActionBourse.execute(
                    principal.clientId(),
                    request.symbol(),
                    request.nom(),
                    request.prix()
            );
            return new ResponseEntity<>(action, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/actions/courant")
    public ResponseEntity<List<ActionBourse>> getMyActions(@AuthenticationPrincipal JwtAuthenticationFilter.MyBankPrincipal principal) {
        List<ActionBourse> actions = listActionBourse.findAllByUser(principal.clientId());
        return ResponseEntity.ok(actions);
    }

    // --------------------- HANDLE BAD REQUEST ---------------------
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}