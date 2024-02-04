package com.gasfgrv.barbearia.adapter.perfil.adapter;

import com.gasfgrv.barbearia.config.IntegrationTestsBaseConfig;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.mocks.perfil.PerfilMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PerfilRepositoryAdapterTest extends IntegrationTestsBaseConfig {

    @Autowired
    private PerfilRepositoryAdapter repository;

    @Test
    void buscarPorNomeCliente(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("cliente".toUpperCase());

        assertThat(perfil)
                .usingDefaultComparator()
                .isEqualTo(PerfilMock.getCliente());
        assertThat(output)
                .contains("Buscando dados do perfil");
    }

    @Test
    void buscarPorNomeBarbeiro(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("barbeiro".toUpperCase());

        assertThat(perfil)
                .usingDefaultComparator()
                .isEqualTo(PerfilMock.getBarbeiro());
        assertThat(output)
                .contains("Buscando dados do perfil");
    }

    @Test
    void buscarPorNomeNulo(CapturedOutput output) {
        Perfil perfil = repository.buscarPorNome("admin".toUpperCase());

        assertThat(perfil)
                .isNull();
        assertThat(output)
                .contains("Buscando dados do perfil");
    }

}
