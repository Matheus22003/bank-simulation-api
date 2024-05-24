package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends RefreshableCRUDRepository<Cliente, Long> {
    public Cliente findByEmail(String email);

}