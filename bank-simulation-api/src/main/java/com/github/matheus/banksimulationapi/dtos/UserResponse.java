package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.ContaBancaria;
import com.github.matheus.banksimulationapi.model.Endereco;
import com.github.matheus.banksimulationapi.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {

    private Long id;
    private String email;
    private String nome;
    private Date dataNascimento;
    private Endereco endereco;
    private ContaBancaria contaBancaria;
    private Set<UserRole> roles;

}