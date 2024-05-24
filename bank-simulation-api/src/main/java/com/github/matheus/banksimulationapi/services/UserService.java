package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.UserRequestDTO;
import com.github.matheus.banksimulationapi.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO userRequest);

    UserResponseDTO getUser();

    List<UserResponseDTO> getAllUser();

}