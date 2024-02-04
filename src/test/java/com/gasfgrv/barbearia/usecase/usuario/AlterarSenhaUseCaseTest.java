package com.gasfgrv.barbearia.usecase.usuario;

import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class AlterarSenhaUseCaseTest {

    public static final Usuario CLIENTE = UsuarioMock.getCliente();

    public static final Usuario BARBEIRO = UsuarioMock.getBarbeiro();

    @InjectMocks
    private AlterarSenhaUseCase alterarSenhaUseCase;

    @Mock
    private UsuarioRepository repository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;


    @Test
    void buscarUsuarioComSucesso() {
        given(repository.buscarPorEmail(anyString())).willReturn(CLIENTE);

        assertThatCode(() -> alterarSenhaUseCase.execute(CLIENTE.getLogin()))
                .doesNotThrowAnyException();
    }

    @Test
    void buscarUsuarioComErro(CapturedOutput output) {
        var login = BARBEIRO.getLogin();
        given(repository.buscarPorEmail(anyString()))
                .willReturn(null);

        assertThatExceptionOfType(UsuarioNaoEncontradoException.class)
                .isThrownBy(() -> alterarSenhaUseCase.execute(login))
                .withMessage("Usuário não foi encontrado");

        assertThat(output)
                .contains("Usuário não foi encontrado");
    }

    @Test
    void alterarSenhaComSucesso() {
        var usuario = BARBEIRO;
        given(repository.buscarPorEmail(anyString())).willReturn(BARBEIRO);
        doNothing().when(repository).salvarUsuario(any());

        alterarSenhaUseCase.execute(usuario.getLogin(), usuario.getSenha());

        verify(repository).salvarUsuario(usuarioArgumentCaptor.capture());
        assertThat(usuarioArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(usuario);
    }

    @Test
    void alterarSenhaComErro(CapturedOutput output) {
        var usuario = CLIENTE;
        given(repository.buscarPorEmail(anyString())).willReturn(null);

        assertThatExceptionOfType(UsuarioNaoEncontradoException.class)
                .isThrownBy(() -> alterarSenhaUseCase.execute(usuario.getLogin(), usuario.getSenha()))
                .withMessage("Usuário não foi encontrado");

        assertThat(output).contains("Usuário não foi encontrado");
        verify(repository, never()).salvarUsuario(any(Usuario.class));
    }

}
