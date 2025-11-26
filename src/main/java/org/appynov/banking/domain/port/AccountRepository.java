package org.appynov.banking.domain.port;

import org.appynov.banking.domain.model.Account;
import org.appynov.banking.domain.model.Client;

import java.util.List;

public interface AccountRepository {
    List<Account> getAccounts();
    Account creationAccount(Account account);
    boolean existsByAccount(String idCompte);
}
