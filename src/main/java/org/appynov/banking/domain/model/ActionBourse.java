package org.appynov.banking.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record ActionBourse(
        @NotBlank(message = "L'identifiant de l'action ne peut pas être vide")
        String id,
        @NotBlank(message = "L'identifiant du client ne peut pas être vide")
        String clientId,
        @NotBlank(message = "Le symbole boursier ne peut pas être vide")
        String symbol,
        @NotBlank(message = "Le nom de l'action ne peut pas être vide")
        String nom,
        @NotBlank(message = "Le prix de l'action ne peut pas être vide")
        double prix,
        @NotBlank(message = "La date de dernière mise à jour ne peut pas être vide")
        Instant lastUpdated
) {
    /**
     * Constructeur .
     *
     * @param id          Identifiant unique de l'action
     * @param clientId    Identifiant du client
     * @param symbol      Symbole bourse
     * @param nom         Nom complet de l'action
     * @param prix        Prix de l'action
     * @param lastUpdated Date et heure de la dernière mise à jour
     */
    public ActionBourse(String id,String clientId, String symbol, String nom, double prix, Instant lastUpdated) {
        this.id = id;
        this.clientId = clientId;
        this.symbol = symbol;
        this.nom = nom;
        this.prix = prix;
        this.lastUpdated = lastUpdated;
    }
    /**
     * Constructeur qui génère automatiquement un ID unique pour l'action.
     *
     * @param clientId    Identifiant du client
     * @param symbol      Symbole bourse
     * @param nom         Nom complet de l'action
     * @param prix        Prix de l'action
     * @param lastUpdated Date et heure de la dernière mise à jour
     */
    public ActionBourse(String clientId, String symbol, String nom, double prix, Instant lastUpdated) {
        this(UlidCreator.getUlid().toString(), clientId, symbol, nom, prix, lastUpdated);
    }
}
