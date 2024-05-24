package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.UserRole;
import jakarta.validation.constraints.NotNull;
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
public class UserRequestDTO {

    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String nome;

    @NotNull
    private Date dataNascimento;

    @NotNull
    private String password;

    private Set<UserRole> roles;

}