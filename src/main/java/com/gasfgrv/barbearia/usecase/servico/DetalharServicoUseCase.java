package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoNaoEncontradoException;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class DetalharServicoUseCase {

    private final ServicoRepository repository;

    public Servico execute(UUID idServico) {
        log.info("Obtendo mais detalhes do serviço");
        return Optional.ofNullable(repository.detalharServico(idServico))
                .orElseThrow(() -> {
                    log.error("Serviço não foi encontrado");
                    return new ServicoNaoEncontradoException();
                });
    }

}
