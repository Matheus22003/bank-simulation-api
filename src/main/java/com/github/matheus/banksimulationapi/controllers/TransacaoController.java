package com.github.matheus.banksimulationapi.controllers;

import com.github.matheus.banksimulationapi.dtos.ClienteResponseDTO;
import com.github.matheus.banksimulationapi.dtos.TransacaoDto;
import com.github.matheus.banksimulationapi.services.TransacoesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/api/v1/transacoes")
public class TransacaoController {

    @Autowired
    private TransacoesService transacoesService;

    @PostMapping("")
    public ResponseEntity<ClienteResponseDTO> transacao(@RequestBody @Valid TransacaoDto transacaoDto) {
        try {
            ClienteResponseDTO userResponse = transacoesService.transacionar(transacaoDto);
            return ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacaoById(@PathVariable("id") Long id) {
        transacoesService.deleteById(id);
        return ok().build();
    }

}
