package com.github.matheus.banksimulationapi;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepositoryImpl;
import com.github.matheus.banksimulationapi.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = RefreshableCRUDRepositoryImpl.class)
@SpringBootApplication
public class BankSimulationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSimulationApiApplication.class, args);
    }

}
