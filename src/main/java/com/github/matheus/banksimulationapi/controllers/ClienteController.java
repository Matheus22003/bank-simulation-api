package com.github.matheus.banksimulationapi.controllers;

import com.github.matheus.banksimulationapi.dtos.ClienteDTO;
import com.github.matheus.banksimulationapi.dtos.ClienteResponseDTO;
import com.github.matheus.banksimulationapi.services.ClientesService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@RequestBody @Valid ClienteDTO transacaoDto) {
        try {
            ClienteResponseDTO userResponse = clientesService.save(transacaoDto);
            return ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<ClienteResponseDTO> update(@RequestBody @Valid ClienteDTO transacaoDto) {
        try {
            ClienteResponseDTO userResponse = clientesService.update(transacaoDto);
            return ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        return ok(clientesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteById(@PathVariable("id") Long id) {
        return ok(clientesService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable("id") Long id) {
        clientesService.deleteById(id);
        return ok().build();
    }


}
