package com.gasfgrv.barbearia.domain.servico.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Servico {
    @EqualsAndHashCode.Include
    private UUID id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private int duracao;
    private boolean ativo;
}
