package org.appynov.banking.domain.model;

import aQute.bnd.annotation.spi.ServiceConsumer;
import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.validation.constraints.NotBlank;

public record User(
        @NotBlank(message = "L'identifiant utilisateur ne peut pas être vide")
        String id,
        @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
        String username,
        @NotBlank(message = "Le hash du mot de passe ne peut pas être vide")
        String mdpHash,
        @NotBlank(message = "Idf de l'utilisateur ne peuvent pas être vides")
        String clientId
) {
    /**
     * Constructeur
     * @param id        Identifiant unique de l'utilisateur
     * @param username  Nom d'utilisateur
     * @param mdpHash   Mot de passe hashé
     * @param clientId  Identifiant du client associé
     */
    public User (String id, String username, String mdpHash, String clientId) {
        this.id = id;
        this.username=username;
        this.mdpHash=mdpHash;
        this.clientId=clientId;
    }
    /**
     * Constructeur qui génère automatiquement un ID unique pour l'utilisateur.
     * @param username Nom d'utilisateur
     * @param mdpHash  Mot de passe hashé
     * @param clientId Identifiant du client associé
     */
    public User(String username, String mdpHash, String clientId){
        this(UlidCreator.getUlid().toString(), username, mdpHash, clientId);
    }
}
