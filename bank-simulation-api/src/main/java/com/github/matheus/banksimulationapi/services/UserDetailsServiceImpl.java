package com.github.matheus.banksimulationapi.services;

import com.github.matheus.banksimulationapi.helpers.CustomUserDetails;
import com.github.matheus.banksimulationapi.model.User;
import com.github.matheus.banksimulationapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Não foi possivel encontrar o usuario!!!");
        }
        return new CustomUserDetails(user);
    }
}