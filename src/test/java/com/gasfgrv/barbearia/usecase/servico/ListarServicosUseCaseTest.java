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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class, OutputCaptureExtension.class})
class ListarServicosUseCaseTest {

    @InjectMocks
    private ListarServicosUseCase listarServicosUseCase;

    @Mock
    private ServicoRepository repository;

    @Test
    void consultarServicosComItensNaBase(CapturedOutput output) {
        var servicoAtivo = ServicoMock.getServicoAtivo();
        given(repository.listarServicos(true)).willReturn(Collections.singletonList(servicoAtivo));

        var servicos = listarServicosUseCase.execute();

        assertThat(output).contains("Obtendo todos serviços");

        assertThat(servicos).hasSize(1);
        assertThat(servicos).usingRecursiveFieldByFieldElementComparator().contains(servicoAtivo);
        verify(repository, times(1)).listarServicos(true);
    }

    @Test
    void consultarServicosSemItensNaBase(CapturedOutput output) {
        given(repository.listarServicos(true)).willReturn(Collections.emptyList());

        var servicos = listarServicosUseCase.execute();

        assertThat(output).contains("Obtendo todos serviços");

        assertThat(servicos).isEmpty();
        verify(repository, times(1)).listarServicos(true);
    }

}