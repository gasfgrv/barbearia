package com.gasfgrv.barbearia.mocks.servico;

import com.gasfgrv.barbearia.domain.servico.model.Servico;

import java.math.BigDecimal;
import java.util.UUID;

public class ServicoMock {

    private static final UUID ID = UUID.fromString("12309792-8ac3-42af-ab54-856c69e738e6");

    public static Servico getServicoAtivo() {
        var servico = new Servico();
        servico.setId(ID);
        servico.setNome("Serviço Ativo");
        servico.setPreco(BigDecimal.valueOf(50));
        servico.setDescricao("Serviço de testes - ativo");
        servico.setDuracao(30);
        servico.setAtivo(true);
        return servico;
    }

    public static Servico getServicoInativo() {
        var servico = new Servico();
        servico.setId(ID);
        servico.setNome("Serviço Inativo");
        servico.setPreco(BigDecimal.valueOf(50));
        servico.setDescricao("Serviço de testes - inativo");
        servico.setDuracao(30);
        servico.setAtivo(false);
        return servico;
    }

}
