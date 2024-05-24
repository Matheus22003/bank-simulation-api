package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.ContaBancaria;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private ContaBancaria contaBancaria;

}
