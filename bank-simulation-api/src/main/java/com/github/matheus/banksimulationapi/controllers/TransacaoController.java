package com.github.matheus.banksimulationapi.controllers;

import com.github.matheus.banksimulationapi.dtos.TransacaoDto;
import com.github.matheus.banksimulationapi.dtos.UserResponse;
import com.github.matheus.banksimulationapi.services.TransacoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {

    @Autowired
    private TransacoesService transacoesService;

    @PostMapping("")
    public ResponseEntity<UserResponse> debitar(@RequestBody @Valid TransacaoDto transacaoDto) {
        try {
            UserResponse userResponse = transacoesService.transacionar(transacaoDto);
            return ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TransacaoDto>> getAllTransacoes() {
        return ok(transacoesService.getAllTransacoes());
    }


}
