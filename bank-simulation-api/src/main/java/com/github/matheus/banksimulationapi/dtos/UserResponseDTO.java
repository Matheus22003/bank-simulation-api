package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDTO {

    private Long id;
    private String email;
    private String nome;
    private Set<UserRole> roles;

}