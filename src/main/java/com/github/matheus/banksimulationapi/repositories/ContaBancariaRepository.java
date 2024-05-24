package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.ContaBancaria;
import com.github.matheus.banksimulationapi.model.Transacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends RefreshableCRUDRepository<ContaBancaria, Long> {

    public ContaBancaria findByAgenciaAndNumero(String agencia, String numero);

    public ContaBancaria findByTransacaosIsContaining(Transacao transacao);

}