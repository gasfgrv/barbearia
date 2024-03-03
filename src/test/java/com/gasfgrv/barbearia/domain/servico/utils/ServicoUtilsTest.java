package com.gasfgrv.barbearia.domain.servico.utils;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.mocks.servico.ServicoMock;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ServicoUtilsTest {

    @Test
    void existemServicosSemelhantesComServicosIguais() {
        List<Servico> servicos = Collections.singletonList(ServicoMock.getServicoAtivo());
        assertThat(ServicoUtils.existemServicosSemelhantes(servicos, "Serviço Ativo")).isTrue();
    }

    @Test
    void existemServicosSemelhantesComServicosDiferentes() {
        List<Servico> servicos = Collections.singletonList(ServicoMock.getServicoAtivo());
        assertThat(ServicoUtils.existemServicosSemelhantes(servicos, "Manicure")).isFalse();
    }

    @Test
    void existemServicosSemelhantesComListaVazia() {
        List<Servico> servicos = List.of();
        assertThat(ServicoUtils.existemServicosSemelhantes(servicos, "Corte de Cabelo")).isFalse();
    }

    @Test
    void testFormataTexto() {
        String textoOriginal = "corte de cabelo";
        String textoFormatado = ServicoUtils.formataTexto(textoOriginal);
        assertThat(textoFormatado).isEqualTo("Corte de cabelo");
    }
}