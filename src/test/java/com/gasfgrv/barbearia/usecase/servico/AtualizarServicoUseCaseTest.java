package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoNaoEncontradoException;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import com.gasfgrv.barbearia.mocks.servico.ServicoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class AtualizarServicoUseCaseTest {

    @InjectMocks
    private AtualizarServicoUseCase atualizarServicoUseCase;

    @Mock
    private ServicoRepository repository;

    @Captor
    private ArgumentCaptor<Servico> captor;

    @Test
    void atualizarServico(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        servicoAtivo.setPreco(BigDecimal.TEN);
        given(repository.detalharServico(any(UUID.class))).willReturn(servicoAtivo);

        atualizarServicoUseCase.execute(UUID.randomUUID(), servicoAtivo);

        assertThat(output).doesNotContain("Não foi possivel atualizar o serviço");

        verify(repository, times(1)).atualizarServico(any(UUID.class), captor.capture());
        assertThat(captor.getValue().getPreco()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    void reativarServico(CapturedOutput output) {
        var servicoInativo = ServicoMock.getServicoInativo();
        given(repository.detalharServico(any(UUID.class))).willReturn(servicoInativo);

        atualizarServicoUseCase.execute(UUID.randomUUID(), null);

        assertThat(output).doesNotContain("Não foi possivel atualizar o serviço");

        verify(repository, times(1)).atualizarServico(any(UUID.class), captor.capture());
        assertThat(captor.getValue().isAtivo()).isTrue();
    }

    @Test
    void atualizarServicoNaoExistente(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.detalharServico(any(UUID.class))).willReturn(null);

        assertThatExceptionOfType(ServicoNaoEncontradoException.class)
                .isThrownBy(() -> atualizarServicoUseCase.execute(UUID.randomUUID(), servicoAtivo))
                .withMessage("Serviço não encontrado");

        assertThat(output).contains("Não foi possivel atualizar o serviço");
    }

}