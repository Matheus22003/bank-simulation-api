package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.Transacao;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends RefreshableCRUDRepository<Transacao, Long> {

}