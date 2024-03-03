package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoExistenteException;
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

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class NovoServicoUseCaseTest {

    @InjectMocks
    private NovoServicoUseCase novoServicoUseCase;

    @Mock
    private ServicoRepository repository;

    @Captor
    private ArgumentCaptor<Servico> captor;


    @Test
    void salvarNovoServico(CapturedOutput output) {
        Servico servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.listarServicos(true)).willReturn(Collections.emptyList());
        given(repository.novoServico(any(Servico.class))).willReturn(servicoAtivo);

        var servico = novoServicoUseCase.execute(servicoAtivo);

        assertThat(output).contains("Buscando por serviços semelhantes",
                "Salvando o serviço");

        verify(repository, times(1)).listarServicos(true);
        verify(repository, times(1)).novoServico(captor.capture());

        var value = captor.getValue();

        assertThat(value).usingRecursiveComparison().isEqualTo(servicoAtivo);
        assertThat(servico).isNotNull();
    }

    @Test
    void salvarServicoExistente(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.listarServicos(true)).willReturn(Collections.singletonList(servicoAtivo));

        assertThatExceptionOfType(ServicoExistenteException.class)
                .isThrownBy(() -> novoServicoUseCase.execute(servicoAtivo))
                .withMessage("Não foi possível inserir este serviço, já existe um semelhante");

        assertThat(output).doesNotContain("Salvando o serviço");
    }

}