package com.gasfgrv.barbearia.adapter.usuario.service;

import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.config.IntegrationTestsBaseConfig;
import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioServiceTest extends IntegrationTestsBaseConfig {

    public static final UsuarioEntity BARBEIRO = UsuarioMock.getBarbeiroEntity();
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JpaUsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        BARBEIRO.setSenha(passwordEncoder.encode(BARBEIRO.getSenha()));
        repository.save(BARBEIRO);
    }

    @Test
    void buscarUsuarioComSucesso() {
        String username = BARBEIRO.getUsername();
        var userDetails = usuarioService.loadUserByUsername(username);

        assertThat(userDetails)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(BARBEIRO);
    }

    @Test
    void buscarUsuarioLancandoUsuarioNaoEncontradoException() {
        String username = BARBEIRO.getUsername().replace("@", "_");

        assertThatExceptionOfType(UsuarioNaoEncontradoException.class)
                .isThrownBy(() -> usuarioService.loadUserByUsername(username));
    }

}
