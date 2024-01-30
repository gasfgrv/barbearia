package com.gasfgrv.barbearia.adapter.usuario.adapter;

import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.config.IntegrationTestsBaseConfig;
import com.gasfgrv.barbearia.mocks.usuario.model.UsuarioMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;


@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioRepositoryAdapterTest extends IntegrationTestsBaseConfig {
    @Autowired
    private UsuarioRepositoryAdapter usuarioRepositoryAdapter;

    @Autowired
    private JpaUsuarioRepository repository;

    @Order(1)
    @Test
    void salvarCliente(CapturedOutput output) {
        usuarioRepositoryAdapter.salvarUsuario(UsuarioMock.getCliente());

        Assertions
                .assertThat(repository.count())
                .isPositive();

        Assertions
                .assertThat(output)
                .contains("Salvando dados do usuário");
    }

    @Order(2)
    @Test
    void salvarBarbeiro(CapturedOutput output) {
        usuarioRepositoryAdapter.salvarUsuario(UsuarioMock.getBarbeiro());

        Assertions
                .assertThat(repository.count())
                .isGreaterThan(1);

        Assertions
                .assertThat(output)
                .contains("Salvando dados do usuário");
    }

    @Order(3)
    @Test
    void buscarCliente(CapturedOutput output) {
        var email = UsuarioMock.getCliente().getLogin();
        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        Assertions
                .assertThat(usuario)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getCliente());

        Assertions
                .assertThat(output)
                .contains("Buscando usuário por login");
    }

    @Order(4)
    @Test
    void buscarBarbeiro(CapturedOutput output) {
        var email = UsuarioMock.getBarbeiro().getLogin();
        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        Assertions
                .assertThat(usuario)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getBarbeiro());

        Assertions
                .assertThat(output)
                .contains("Buscando usuário por login");
    }

    @Order(5)
    @Test
    void buscarNulo(CapturedOutput output) {
        var email = "email@email.com";
        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        Assertions
                .assertThat(usuario)
                .isNull();

        Assertions
                .assertThat(output)
                .contains("Buscando usuário por login");
    }
}