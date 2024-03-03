package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoNaoEncontradoException;
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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class DetalharServicoUseCaseTest {

    @InjectMocks
    private DetalharServicoUseCase detalharServicoUseCase;

    @Mock
    private ServicoRepository repository;

    @Test
    void detalharServicoExistenteNaBase(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.detalharServico(any(UUID.class))).willReturn(servicoAtivo);

        var servico = detalharServicoUseCase.execute(UUID.randomUUID());

        assertThat(output).contains("Obtendo mais detalhes do serviço");

        assertThat(servico).isNotNull();
        assertThat(servico).usingRecursiveComparison().isEqualTo(servicoAtivo);
        verify(repository, times(1)).detalharServico(any(UUID.class));
    }

    @Test
    void detalharServicoNaoExistenteNaBase(CapturedOutput output) {
        given(repository.detalharServico(any(UUID.class))).willReturn(null);

        assertThatExceptionOfType(ServicoNaoEncontradoException.class)
                .isThrownBy(() -> detalharServicoUseCase.execute(UUID.randomUUID()))
                .withMessage("Serviço não encontrado");

        assertThat(output).contains("Obtendo mais detalhes do serviço",
                "Serviço não foi encontrado");

        verify(repository, times(1)).detalharServico(any(UUID.class));
    }

}