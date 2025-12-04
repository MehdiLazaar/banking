package org.appynov.banking.infrastructure.application.driving.web.error;

import org.appynov.banking.domain.usecase.FindUser;
import org.appynov.banking.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 400 - Erreur de validation
     * Provoquée par : @Valid, DTO invalides, champs manquants...
     * On retourne toutes les erreurs sous forme d’un message unique.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + " : " + err.getDefaultMessage())
                .reduce((a, b) -> a + ", " + b)
                .orElse("Invalid request");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        message
                ));
    }

    /**
     * 409 - ClientAlreadyExistsException
     * Quand on essaie d'ajouter un client déjà existant.
     */
    @ExceptionHandler(org.appynov.banking.exception.ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClientExists(org.appynov.banking.exception.ClientAlreadyExistsException ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * 404 - Client non trouvé
     * Exception créée par ton domaine.
     */
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ClientNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ex.getMessage()
                ));
    }

    /**
     * 500 - Exception générique
     * Catch-all final → si une erreur non gérée remonte.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Erreur interne du serveur"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    /**
     * 404 - Route inexistante
     * Lancée par Spring lorsque l’URL n’existe pas.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        "Resource not found"
                ));
    }

    /**
     * 405 - Mauvaise méthode HTTP
     * Exemple : POST sur une route acceptant seulement GET.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
                        "Method not allowed"
                ));
    }

    /**
     * 401 - Mauvaise authentification
     * Utilisé par ton filtre JWT.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        "Authentication required: " + ex.getMessage()
                ));
    }

    /**
     * 403 - Interdit
     * Utilisé si l’utilisateur est authentifié mais n’a pas les droits.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        "Access denied: " + ex.getMessage()
                ));
    }
    @ExceptionHandler(FindUser.UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(FindUser.UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        "Nom d'utilisateur ou mot de passe incorrect"
                ));
    }

}
