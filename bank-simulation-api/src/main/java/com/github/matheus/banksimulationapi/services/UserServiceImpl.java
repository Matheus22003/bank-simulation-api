package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.dtos.UserRequest;
import com.github.matheus.banksimulationapi.dtos.UserResponse;
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

    public static final int LENGTH_AGENCIA = 4;
    public static final int LENGHT_NUMERO = 6;
    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserResponse saveUser(UserRequest userRequest) {
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
            setContaBancaria(user);
            savedUser = userRepository.save(user);
        }
        userRepository.refresh(savedUser);
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();
        User user = userRepository.findByEmail(usernameFromAccessToken);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = (List<User>) userRepository.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponse>>() {
        }.getType();
        List<UserResponse> userResponses = modelMapper.map(users, setOfDTOsType);
        return userResponses;
    }

    private static void setContaBancaria(User user) {
        user.setContaBancaria(ContaBancaria.builder()
                .agencia(generateNumerosAleatorioesString(LENGTH_AGENCIA))
                .numero(generateNumerosAleatorioesString(LENGHT_NUMERO))
                .build());
    }

    private static String generateNumerosAleatorioesString(int quantidadeNumeros) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }


}