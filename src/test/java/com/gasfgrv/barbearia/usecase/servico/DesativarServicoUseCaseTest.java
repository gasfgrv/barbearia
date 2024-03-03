package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import com.gasfgrv.barbearia.mocks.servico.ServicoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class DesativarServicoUseCaseTest {

    @InjectMocks
    private DesativarServicoUseCase desativarServicoUseCase;

    @Mock
    private ServicoRepository repository;

    @Test
    void desativarServicoExistenteNaBase(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.detalharServico(any(UUID.class))).willReturn(servicoAtivo);
        doNothing().when(repository).desativarServico(any(UUID.class));

        desativarServicoUseCase.execute(UUID.randomUUID());

        assertThat(output).contains("Desativando serviço");

        verify(repository, times(1)).desativarServico(any(UUID.class));
    }

    @Test
    void desativarServicoNaoExistenteNaBase(CapturedOutput output) {
        given(repository.detalharServico(any(UUID.class))).willReturn(null);
        doNothing().when(repository).desativarServico(any(UUID.class));

        desativarServicoUseCase.execute(UUID.randomUUID());

        assertThat(output).contains("Desativando serviço");

        verify(repository, never()).desativarServico(any(UUID.class));
    }
}