package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RefreshableCRUDRepository<User, Long>  {

    public User findByEmail(String email);

    public User findFirstById(Long id);


}