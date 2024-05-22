package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.TransacaoDto;
import com.github.matheus.banksimulationapi.dtos.UserResponse;
import com.github.matheus.banksimulationapi.model.Transacao;
import com.github.matheus.banksimulationapi.model.User;
import com.github.matheus.banksimulationapi.model.enums.TipoTransacao;
import com.github.matheus.banksimulationapi.repositories.TransacoesRepository;
import com.github.matheus.banksimulationapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TransacoesService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransacoesRepository transacoesRepository;

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public UserResponse transacionar(TransacaoDto transacaoDto) {
        Transacao mapTransacao = modelMapper.map(transacaoDto, Transacao.class);
        mapTransacao.setTipoTransacao(getTipoTransacao(mapTransacao));
        User savedUser = userRepository.findByEmail(transacaoDto.getEmail());
        if (savedUser == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        mapTransacao.setContaBancaria(savedUser.getContaBancaria());
        savedUser.getContaBancaria().getTransacaos().add(mapTransacao);
        savedUser = userRepository.save(savedUser);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public List<TransacaoDto> getAllTransacoes() {
        return transacoesRepository.findAllByContaBancariaId(userService.getUser().getContaBancaria().getId())
                .stream().map(transacao -> modelMapper.map(transacao, TransacaoDto.class)).collect(toList());
    }

    private TipoTransacao getTipoTransacao(Transacao transacao) {
        if (transacao.getValor() > 0) {
            return TipoTransacao.CREDITO;
        }
        return TipoTransacao.DEBITO;
    }


}
