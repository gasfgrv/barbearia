package com.gasfgrv.barbearia.usecase.usuario;

import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.TokenService;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class AutenticarUsuarioUseCaseTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
            ".eyJpc3MiOiJMb2dpbiIsInN1YiI6ImJhcmJlaXJvQHRlc3RlLmNvbSIsImV4cCI6MTcwNTc4MTIzNX0" +
            ".rvpDERjJYvZU47gq5LFJmAN-l0seXl0JfoxdBsKv-8Q";

    @InjectMocks
    private AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    @Mock
    private TokenService tokenService;

    @Test
    void autenticarCliente(CapturedOutput output) {
        autenticarUsuario(output, UsuarioMock.getCliente());
    }

    @Test
    void autenticarBarbeiro(CapturedOutput output) {
        autenticarUsuario(output, UsuarioMock.getBarbeiro());
    }

    private void autenticarUsuario(CapturedOutput output, Usuario usuario) {
        given(tokenService.gerarToken(Mockito.any(Usuario.class))).willReturn(TOKEN);

        String token = autenticarUsuarioUseCase.autenticar(usuario);

        assertThat(output).contains("Gerando token para as credenciais do usuário");
        assertThat(token).isNotEmpty().isNotNull();
    }

}
