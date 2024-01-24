package com.gasfgrv.barbearia.adapter.perfil.adapter;

import com.gasfgrv.barbearia.config.TestcontainersConfig;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.mocks.perfil.model.PerfilMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.testcontainers.junit.jupiter.Testcontainers;


@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PerfilRepositoryAdapterTest extends TestcontainersConfig {
    @Autowired
    private PerfilRepositoryAdapter repository;

    @Test
    void buscarPorNomeCliente(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("cliente".toUpperCase());
        Assertions
                .assertThat(perfil)
                .usingDefaultComparator()
                .isEqualTo(PerfilMock.getCliente());

        Assertions
                .assertThat(output)
                .contains("Buscando dados do perfil");
    }

    @Test
    void buscarPorNomeBarbeiro(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("barbeiro".toUpperCase());
        Assertions
                .assertThat(perfil)
                .usingDefaultComparator()
                .isEqualTo(PerfilMock.getBarbeiro());

        Assertions
                .assertThat(output)
                .contains("Buscando dados do perfil");
    }

    @Test
    void buscarPorNomeNulo(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("admin".toUpperCase());
        Assertions
                .assertThat(perfil)
                .isNull();

        Assertions
                .assertThat(output)
                .contains("Buscando dados do perfil");
    }
}