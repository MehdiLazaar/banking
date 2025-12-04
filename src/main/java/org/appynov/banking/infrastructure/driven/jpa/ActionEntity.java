package org.appynov.banking.infrastructure.driven.jpa;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "action")
public class ActionEntity {

    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "clientId", length = 26, nullable = false)
    private String clientId;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(length = 100)
    private String nom;

    @Column(nullable = false)
    private double prix;

    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;

    public ActionEntity() {
    }

    public ActionEntity(String id,String clientId, String symbol, String nom, double prix, Instant lastUpdated) {
        this.id = id;
        this.clientId = clientId;
        this.symbol = symbol;
        this.nom = nom;
        this.prix = prix;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }
    public String getClientId() {
        return clientId;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getNom() {
        return nom;
    }
    public double getPrix() {
        return prix;
    }
    public Instant getLastUpdated() {
        return lastUpdated;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
