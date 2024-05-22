package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.UserRequest;
import com.github.matheus.banksimulationapi.dtos.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();

}