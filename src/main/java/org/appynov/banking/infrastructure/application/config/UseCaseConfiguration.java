package org.appynov.banking.infrastructure.application.config;
import org.appynov.banking.domain.model.Client;
import org.appynov.banking.domain.port.AccountRepository;
import org.appynov.banking.domain.port.ClientRepository;
import org.appynov.banking.domain.usecase.CreateAccount;
import org.appynov.banking.domain.usecase.CreateClient;
import org.appynov.banking.domain.usecase.ListAccounts;
import org.appynov.banking.domain.usecase.ListClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ListClients listClients(ClientRepository clientRepository) {
        return new ListClients(clientRepository);
    }
    @Bean
    public CreateClient createClient(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }
    @Bean
    public CreateAccount createAccount(AccountRepository accountRepository) {return new CreateAccount(accountRepository); }
    @Bean
    public ListAccounts listAccounts(AccountRepository accountRepository) {return new ListAccounts(accountRepository); }
}
