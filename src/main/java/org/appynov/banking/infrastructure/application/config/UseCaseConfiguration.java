package org.appynov.banking.infrastructure.application.config;
import org.appynov.banking.domain.port.AccountRepository;
import org.appynov.banking.domain.port.ActionBourseRepository;
import org.appynov.banking.domain.port.ClientRepository;
import org.appynov.banking.domain.port.UserRepository;
import org.appynov.banking.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public ListClients listClientsUseCase(ClientRepository clientRepository) {
        return new ListClients(clientRepository);
    }
    @Bean
    public CreateClient createClientUseCase(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }
    @Bean
    public CreateAccount createAccountUseCase(AccountRepository accountRepository) {
        return new CreateAccount(accountRepository);
    }
    @Bean
    public ListAccounts listAccountsUseCase(AccountRepository accountRepository) {
        return new ListAccounts(accountRepository);
    }
    @Bean
    public CreateUser createUserUseCase(UserRepository userRepository, CreateClient createClient) {
        return new CreateUser(userRepository, createClient);
    }
    @Bean
    public FindUser findUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new FindUser(userRepository, passwordEncoder);
    }
    @Bean
    public CreateActionBourse createActionBourseUseCase(ActionBourseRepository actionRepository) {
        return new CreateActionBourse(actionRepository);
    }
    @Bean
    public ListActionBourse findActionBourseUseCase(ActionBourseRepository actionRepository) {
        return new ListActionBourse(actionRepository);
    }
}
