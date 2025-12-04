package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.Account;
import org.appynov.banking.domain.model.Client;

import java.util.List;

public interface AccountRepository {
    /**
     * Recupere la liste de tous les comptes existants.
     * @return Une liste d'objets Account
     * */
    List<Account> getAccounts();
    /**
     * Cree un nouveau compte bancaire.
     * @param account L objet Account à creer
     * @return L'objet Account créé
     */
    Account creationAccount(Account account);
    /**
     * Vérifie si un compte existe déjà en fonction de son identifiant.
     *
     * @param idCompte L identifiant du compte e verifier
     * @return true si le compte existe, false sinon
     */
    boolean existsByAccount(String idCompte);
}
