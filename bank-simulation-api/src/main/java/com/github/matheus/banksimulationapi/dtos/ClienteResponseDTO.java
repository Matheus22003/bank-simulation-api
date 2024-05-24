package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.ContaBancaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private ContaBancaria contaBancaria;
}
