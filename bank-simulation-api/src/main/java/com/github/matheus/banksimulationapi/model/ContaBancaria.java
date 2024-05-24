package com.github.matheus.banksimulationapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contas_bancarias")
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 4, max = 4)
    private String agencia;

    private String numero;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transacao> transacaos = new ArrayList<>();

}
