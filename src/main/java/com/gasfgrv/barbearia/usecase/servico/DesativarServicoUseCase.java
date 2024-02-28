package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class DesativarServicoUseCase {

    private final ServicoRepository repository;

    public void execute(UUID idServico) {
        log.info("Desativando serviço");
        Optional.ofNullable(repository.detalharServico(idServico))
                .map(Servico::getId)
                .ifPresent(repository::desativarServico);
    }

}
