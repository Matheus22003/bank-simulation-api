package com.github.matheus.banksimulationapi.dtos;

import com.github.matheus.banksimulationapi.model.enums.TipoTransacao;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransacaoDto {

    private String descricao;
    
    @NotNull
    private Double valor;

    @NotNull
    private String email;

    private TipoTransacao tipoTransacao;

}
