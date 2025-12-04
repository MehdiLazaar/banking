package org.appynov.banking.infrastructure.application.driving.web;

import jakarta.validation.Valid;
import org.appynov.banking.domain.usecase.CreateUser;
import org.appynov.banking.domain.usecase.FindUser;
import org.appynov.banking.infrastructure.application.driving.web.dto.AuthResponse;
import org.appynov.banking.infrastructure.application.driving.web.dto.LoginRequest;
import org.appynov.banking.infrastructure.application.driving.web.dto.RegisterRequest;
import org.appynov.banking.infrastructure.application.driving.web.security.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CreateUser createUser;
    private final FindUser findUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwt;

    public AuthController(CreateUser createUser,
                          PasswordEncoder passwordEncoder,
                          JwtTokenService jwt,
                          FindUser findUser) {
        this.createUser = createUser;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
        this.findUser = findUser;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        var user = createUser.create(request.username(), encodedPassword, request.lastName(), request.firstName()
        );
        String token = jwt.generate(user.username(),
                Map.of("username", user.username(), "clientId", user.clientId()));
        return new AuthResponse(token, user.username(), user.clientId());
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        try {
            var user = findUser.by(request.username(), request.password());
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nom d'utilisateur ou mot de passe incorrect");
            }
            String token = jwt.generate(user.username(),
                    Map.of("username", user.username(),
                            "clientId", user.clientId()
                    ));
            return new AuthResponse(
                    token,
                    user.username(),
                    user.clientId()
            );
        } catch (Exception e) {
            e.printStackTrace(); // Pour voir l’erreur exacte dans la console
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Nom d'utilisateur ou mot de passe incorrect"
            );
        }
    }


}
