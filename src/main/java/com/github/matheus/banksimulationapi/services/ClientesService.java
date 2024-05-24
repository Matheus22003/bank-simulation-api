package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.ClienteDTO;
import com.github.matheus.banksimulationapi.dtos.ClienteResponseDTO;
import com.github.matheus.banksimulationapi.model.Cliente;
import com.github.matheus.banksimulationapi.model.ContaBancaria;
import com.github.matheus.banksimulationapi.repositories.ClienteRepository;
import com.github.matheus.banksimulationapi.repositories.ContaBancariaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;
    private ModelMapper modelMapper = new ModelMapper();


    public ClienteResponseDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (cliente != null) {
            throw new RuntimeException("Cliente já existente!");
        }
        validarContaBancaria(clienteDTO);
        cliente = modelMapper.map(clienteDTO, Cliente.class);
        Cliente save = clienteRepository.save(cliente);
        return modelMapper.map(save, ClienteResponseDTO.class);
    }

    public ClienteResponseDTO update(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (cliente == null) {
            throw new RuntimeException("Cliente não inexistente!");
        }
        Cliente mapped = modelMapper.map(clienteDTO, Cliente.class);
        mapped.setId(cliente.getId());
        Cliente save = clienteRepository.save(mapped);
        return modelMapper.map(save, ClienteResponseDTO.class);
    }

    private void validarContaBancaria(ClienteDTO clienteDTO) {
        ContaBancaria byAgenciaAndNumero = contaBancariaRepository.findByAgenciaAndNumero(clienteDTO.getContaBancaria().getAgencia(), clienteDTO.getContaBancaria().getNumero());
        if (byAgenciaAndNumero != null) {
            throw new RuntimeException("Conta já existente!");
        }
    }

    public List<ClienteResponseDTO> getAll() {
        List<Cliente> allByNomeContains = Lists.from(clienteRepository.findAll().iterator());
        return allByNomeContains.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class)).toList();
    }

    public ClienteResponseDTO getById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
