package org.appynov.banking.infrastructure.driven.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comptes")
public class AccountEntity {
    @Id
    @Column(name = "id", length = 26)
    private String id;

    @Column(name = "clientId", length = 26)
    private String clientId;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "nom", length = 100)
    private String nom;

    public AccountEntity() {
    }
    public AccountEntity(String id, String clientId, Double balance, String type, String nom) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
        this.type = type;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
