package com.gasfgrv.barbearia.usecase.usuario;

import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import com.gasfgrv.barbearia.mocks.usuario.model.UsuarioMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class AlterarSenhaUseCaseTest {
    @InjectMocks
    private AlterarSenhaUseCase alterarSenhaUseCase;

    @Mock
    private UsuarioRepository repository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;

    @Test
    void buscarUsuarioComSucesso() {
        BDDMockito
                .given(repository.buscarPorEmail(Mockito.anyString()))
                .willReturn(UsuarioMock.getCliente());

        Assertions
                .assertThatCode(() -> alterarSenhaUseCase.execute(UsuarioMock.getCliente().getLogin()))
                .doesNotThrowAnyException();
    }

    @Test
    void buscarUsuarioComErro(CapturedOutput output) {
        var login = UsuarioMock.getBarbeiro().getLogin();

        BDDMockito
                .given(repository.buscarPorEmail(Mockito.anyString()))
                .willReturn(null);

        Assertions
                .assertThatExceptionOfType(UsuarioNaoEncontradoException.class)
                .isThrownBy(() -> alterarSenhaUseCase.execute(login))
                .withMessage("Usuário não foi encontrado");

        Assertions
                .assertThat(output)
                .contains("Usuário não foi encontrado");
    }

    @Test
    void alterarSenhaComSucesso() {
        var usuario = UsuarioMock.getBarbeiro();

        BDDMockito
                .given(repository.buscarPorEmail(Mockito.anyString()))
                .willReturn(UsuarioMock.getBarbeiro());

        BDDMockito
                .doNothing()
                .when(repository)
                .salvarUsuario(Mockito.any());

        alterarSenhaUseCase.execute(usuario.getLogin(), usuario.getSenha());

        Mockito
                .verify(repository)
                .salvarUsuario(usuarioArgumentCaptor.capture());

        Assertions
                .assertThat(usuarioArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(usuario);
    }

    @Test
    void alterarSenhaComErro(CapturedOutput output) {
        var usuario = UsuarioMock.getCliente();

        BDDMockito
                .given(repository.buscarPorEmail(Mockito.anyString()))
                .willReturn(null);

        Assertions
                .assertThatExceptionOfType(UsuarioNaoEncontradoException.class)
                .isThrownBy(() -> alterarSenhaUseCase.execute(usuario.getLogin(), usuario.getSenha()))
                .withMessage("Usuário não foi encontrado");

        Assertions
                .assertThat(output)
                .contains("Usuário não foi encontrado");

        Mockito
                .verify(repository, Mockito.never())
                .salvarUsuario(Mockito.any(Usuario.class));
    }
}