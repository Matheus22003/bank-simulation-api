package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.UserRequestDTO;
import com.github.matheus.banksimulationapi.dtos.UserResponseDTO;
import com.github.matheus.banksimulationapi.model.ContaBancaria;
import com.github.matheus.banksimulationapi.model.User;
import com.github.matheus.banksimulationapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequest) {
        User savedUser = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(encodedPassword);
        User oldUser = userRepository.findByEmail(userRequest.getEmail());
        if (userRequest.getId() != null) {
            if (oldUser != null) {
                oldUser.setId(user.getId());
                oldUser.setPassword(user.getPassword());
                oldUser.setEmail(user.getEmail());
                oldUser.setRoles(user.getRoles());

                savedUser = userRepository.save(oldUser);
                userRepository.refresh(savedUser);
            } else {
                throw new RuntimeException("Não foi encontrado nenhum registro com o id: " + userRequest.getId());
            }
        } else {
            if (oldUser != null) {
                throw new RuntimeException("Usuario com o email: " + userRequest.getEmail() + " já existe!!!");
            }
            savedUser = userRepository.save(user);
        }
        userRepository.refresh(savedUser);
        UserResponseDTO userResponse = modelMapper.map(savedUser, UserResponseDTO.class);
        return userResponse;
    }

    @Override
    public UserResponseDTO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();
        User user = userRepository.findByEmail(usernameFromAccessToken);
        UserResponseDTO userResponse = modelMapper.map(user, UserResponseDTO.class);
        return userResponse;
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> users = (List<User>) userRepository.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponseDTO>>() {
        }.getType();
        List<UserResponseDTO> userResponses = modelMapper.map(users, setOfDTOsType);
        return userResponses;
    }

}