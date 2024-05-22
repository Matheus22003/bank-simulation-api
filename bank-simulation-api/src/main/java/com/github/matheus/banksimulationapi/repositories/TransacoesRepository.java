package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.Transacao;
import com.github.matheus.banksimulationapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacoesRepository extends RefreshableCRUDRepository<Transacao, Long> {

    public List<Transacao> findAllByContaBancariaId(Long id);


}