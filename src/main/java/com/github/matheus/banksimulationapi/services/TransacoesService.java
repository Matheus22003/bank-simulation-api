package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.ClienteResponseDTO;
import com.github.matheus.banksimulationapi.dtos.TransacaoDto;
import com.github.matheus.banksimulationapi.model.Cliente;
import com.github.matheus.banksimulationapi.model.ContaBancaria;
import com.github.matheus.banksimulationapi.model.Transacao;
import com.github.matheus.banksimulationapi.model.enums.TipoTransacao;
import com.github.matheus.banksimulationapi.repositories.ClienteRepository;
import com.github.matheus.banksimulationapi.repositories.ContaBancariaRepository;
import com.github.matheus.banksimulationapi.repositories.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacoesService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public ClienteResponseDTO transacionar(TransacaoDto transacaoDto) {
        Cliente cliente = clienteRepository.findByEmail(transacaoDto.getEmail());
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }
        Transacao map = modelMapper.map(transacaoDto, Transacao.class);
        map.setTipoTransacao(getTipoTransacao(map));
//        map.setContaBancaria(cliente.getContaBancaria());
        cliente.getContaBancaria().getTransacaos().add(map);
        Cliente save = clienteRepository.save(cliente);
        return modelMapper.map(save, ClienteResponseDTO.class);
    }

    private TipoTransacao getTipoTransacao(Transacao transacao) {
        if (transacao.getValor() > 0) {
            return TipoTransacao.CREDITO;
        }
        return TipoTransacao.DEBITO;
    }


    public void deleteById(Long id) {
        Transacao transacao = transacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Transação não encontrada"));
        ContaBancaria byTransacaosIsContaining = contaBancariaRepository.findByTransacaosIsContaining(transacao);
        byTransacaosIsContaining.getTransacaos().stream().filter(transacao1 -> transacao1.getId().equals(transacao.getId())).findFirst().ifPresent(transacao1 -> byTransacaosIsContaining.getTransacaos().remove(transacao1));
        contaBancariaRepository.save(byTransacaosIsContaining);
        transacaoRepository.delete(transacao);
    }
}
