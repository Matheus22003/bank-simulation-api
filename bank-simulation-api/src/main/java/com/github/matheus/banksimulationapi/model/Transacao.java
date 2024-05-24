package com.github.matheus.banksimulationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.matheus.banksimulationapi.model.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    private String descricao;

    private Boolean ativo = true;

//    @JsonIgnore
//    @ManyToOne(cascade = CascadeType.DETACH)
//    @JoinColumn(name = "conta_bancaria_id", referencedColumnName = "id")
//    private ContaBancaria contaBancaria;
}
