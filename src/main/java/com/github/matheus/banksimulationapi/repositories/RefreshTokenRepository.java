package com.github.matheus.banksimulationapi.repositories;

import com.github.matheus.banksimulationapi.helpers.RefreshableCRUDRepository;
import com.github.matheus.banksimulationapi.model.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends RefreshableCRUDRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Long id);
}