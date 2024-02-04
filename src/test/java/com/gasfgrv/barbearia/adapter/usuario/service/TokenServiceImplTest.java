package com.gasfgrv.barbearia.adapter.usuario.service;

import com.gasfgrv.barbearia.adapter.usuario.mapper.UsuarioMapper;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenService, "secret", "test");
    }

    @Test
    void gerarToken() {
        var usuario = UsuarioMock.getBarbeiro();
        given(usuarioMapper.paraEntidade(any(Usuario.class))).willReturn(UsuarioMock.getBarbeiroEntity());

        var token = tokenService.gerarToken(usuario);

        assertThat(token).matches("(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)");
    }

    @Test
    void getSubject() {
        var usuario = UsuarioMock.getBarbeiro();
        var login = usuario.getLogin();
        given(usuarioMapper.paraEntidade(any(Usuario.class))).willReturn(UsuarioMock.getBarbeiroEntity());

        var token = tokenService.gerarToken(usuario);
        var subject = tokenService.getSubject(token);

        assertThat(subject).isEqualTo(login);
    }

}
