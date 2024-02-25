package com.gasfgrv.barbearia.adapter.usuario.adapter;

import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.config.PostgresContainerTestConfiguration;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@Import(PostgresContainerTestConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryAdapterTest {

    public static final Usuario CLIENTE = UsuarioMock.getCliente();
    public static final Usuario BARBEIRO = UsuarioMock.getBarbeiro();

    @Autowired
    private UsuarioRepositoryAdapter usuarioRepositoryAdapter;

    @Autowired
    private JpaUsuarioRepository repository;

    @Order(1)
    @Test
    void salvarCliente(CapturedOutput output) {
        usuarioRepositoryAdapter.salvarUsuario(CLIENTE);

        assertThat(repository.count()).isPositive();
        assertThat(output).contains("Salvando dados do usuário");
    }

    @Order(2)
    @Test
    void salvarBarbeiro(CapturedOutput output) {
        usuarioRepositoryAdapter.salvarUsuario(BARBEIRO);

        assertThat(repository.count()).isGreaterThan(1);
        assertThat(output).contains("Salvando dados do usuário");
    }

    @Order(3)
    @Test
    void buscarCliente(CapturedOutput output) {
        var email = CLIENTE.getLogin();

        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        assertThat(usuario).usingRecursiveComparison().isEqualTo(CLIENTE);
        assertThat(output).contains("Buscando usuário por login");
    }

    @Order(4)
    @Test
    void buscarBarbeiro(CapturedOutput output) {
        var email = BARBEIRO.getLogin();

        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        assertThat(usuario).usingRecursiveComparison().isEqualTo(BARBEIRO);
        assertThat(output).contains("Buscando usuário por login");
    }

    @Order(5)
    @Test
    void buscarNulo(CapturedOutput output) {
        var email = "email@email.com";

        var usuario = usuarioRepositoryAdapter.buscarPorEmail(email);

        assertThat(usuario).isNull();
        assertThat(output).contains("Buscando usuário por login");
    }

}
