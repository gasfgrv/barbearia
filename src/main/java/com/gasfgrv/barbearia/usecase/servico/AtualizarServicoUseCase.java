package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoNaoEncontradoException;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

import static com.gasfgrv.barbearia.domain.servico.utils.ServicoUtils.formataTexto;

@Slf4j
@RequiredArgsConstructor
public class AtualizarServicoUseCase {

    private final ServicoRepository repository;

    public Servico execute(UUID idServico, Servico novoServico) {
        var servicoOptional = Optional.ofNullable(repository.detalharServico(idServico));

        if (servicoOptional.isEmpty()) {
            log.error("Não foi possivel atualizar o serviço");
            throw new ServicoNaoEncontradoException();
        }

        var servico = servicoOptional.get();

        if (servico.isAtivo()) {
            servico.setNome(formataTexto(novoServico.getNome()));
            servico.setPreco(novoServico.getPreco());
            servico.setDescricao(novoServico.getDescricao());
            servico.setDuracao(novoServico.getDuracao());
        }

        servico.setAtivo(true);

        return repository.atualizarServico(idServico, servico);
    }

    public void execute(UUID idServico) {
        this.execute(idServico, null);
    }

}
