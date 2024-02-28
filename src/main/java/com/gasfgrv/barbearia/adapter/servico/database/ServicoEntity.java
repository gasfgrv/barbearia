package com.gasfgrv.barbearia.adapter.servico.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "servico")
@Getter
@Setter
@NoArgsConstructor
public class ServicoEntity implements Serializable {
    @Id
    private UUID id;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private int duracao;
    private boolean ativo;
}
