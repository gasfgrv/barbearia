package com.gasfgrv.barbearia.usecase.perfil;

import com.gasfgrv.barbearia.domain.perfil.exception.PerfilNaoEncontradoException;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.mocks.perfil.model.PerfilMock;
import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class ObterPerfilUseCaseTest {

    @InjectMocks
    private ObterPerfilUseCase obterPerfilUseCase;

    @Mock
    private PerfilRepository repository;

    @Test
    void obterPerfilCliente() {
        BDDMockito
                .given(repository.buscarPorNome(Mockito.anyString()))
                .willReturn(PerfilMock.getCliente());

        Perfil perfil = obterPerfilUseCase.obterPerfil("cliente");

        Assertions
                .assertThat(perfil.getId())
                .isEqualTo(1);

        Assertions
                .assertThat(perfil.getNome())
                .isEqualToIgnoringCase("Cliente");

        Assertions
                .assertThat(perfil.getNome())
                .isEqualToIgnoringCase("Cliente");

        BDDMockito
                .verify(repository, Mockito.times(1))
                .buscarPorNome(Mockito.anyString());
    }

    @Test
    void obterPerfilBarbeiro() {
        BDDMockito
                .given(repository.buscarPorNome(Mockito.anyString()))
                .willReturn(PerfilMock.getBarbeiro());

        Perfil perfil = obterPerfilUseCase.obterPerfil("barbeiro");

        Assertions
                .assertThat(perfil.getId())
                .isEqualTo(2);

        Assertions
                .assertThat(perfil.getNome())
                .isEqualToIgnoringCase("barbeiro");

        Assertions
                .assertThat(perfil.getNome())
                .isEqualToIgnoringCase("barbeiro");

        BDDMockito
                .verify(repository, Mockito.times(1))
                .buscarPorNome(Mockito.anyString());
    }

    @Test
    void obterPerfilPerfilNaoEncontradoException(CapturedOutput output) {
        BDDMockito
                .given(repository.buscarPorNome(Mockito.anyString()))
                .willReturn(null);

        Assertions
                .assertThatExceptionOfType(PerfilNaoEncontradoException.class)
                .isThrownBy(() -> obterPerfilUseCase.obterPerfil("admin"))
                .withMessage("Perfil não encontrado");

        Assertions
                .assertThat(output)
                .contains("Perfil não encontrado");
    }
}