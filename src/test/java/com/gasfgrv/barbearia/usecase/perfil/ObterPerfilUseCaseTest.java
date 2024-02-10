package com.gasfgrv.barbearia.usecase.perfil;

import com.gasfgrv.barbearia.domain.perfil.exception.PerfilNaoEncontradoException;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import com.gasfgrv.barbearia.mocks.perfil.PerfilMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class ObterPerfilUseCaseTest {

    @InjectMocks
    private ObterPerfilUseCase obterPerfilUseCase;

    @Mock
    private PerfilRepository repository;

    @Test
    void obterPerfilCliente() {
        given(repository.buscarPorNome(anyString())).willReturn(PerfilMock.getCliente());

        Perfil perfil = obterPerfilUseCase.obterPerfil("cliente");

        assertThat(perfil.getId()).isEqualTo(1);
        assertThat(perfil.getNome()).isEqualToIgnoringCase("Cliente");
        verify(repository, times(1)).buscarPorNome(anyString());
    }

    @Test
    void obterPerfilBarbeiro() {
        given(repository.buscarPorNome(anyString())).willReturn(PerfilMock.getBarbeiro());

        Perfil perfil = obterPerfilUseCase.obterPerfil("barbeiro");

        assertThat(perfil.getId()).isEqualTo(2);
        assertThat(perfil.getNome()).isEqualToIgnoringCase("barbeiro");
        verify(repository, times(1)).buscarPorNome(anyString());
    }

    @Test
    void obterPerfilPerfilNaoEncontradoException(CapturedOutput output) {
        given(repository.buscarPorNome(anyString())).willReturn(null);

        assertThatExceptionOfType(PerfilNaoEncontradoException.class)
                .isThrownBy(() -> obterPerfilUseCase.obterPerfil("admin"))
                .withMessage("Perfil não encontrado");

        assertThat(output).contains("Perfil não encontrado");
    }

}
