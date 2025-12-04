package org.appynov.banking.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;

import java.time.Instant;

public record ActionBourse(
        String id,
        String clientId,
        String symbol,
        String nom,
        double prix,
        Instant lastUpdated
) {
    public ActionBourse(String id,String clientId, String symbol, String nom, double prix, Instant lastUpdated) {
        this.id = id;
        this.clientId = clientId;
        this.symbol = symbol;
        this.nom = nom;
        this.prix = prix;
        this.lastUpdated = lastUpdated;
    }
    public ActionBourse(String clientId, String symbol, String nom, double prix, Instant lastUpdated) {
        this(UlidCreator.getUlid().toString(), clientId, symbol, nom, prix, lastUpdated);
    }
}
