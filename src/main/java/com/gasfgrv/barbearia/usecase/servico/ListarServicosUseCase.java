package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ListarServicosUseCase {

    private final ServicoRepository repository;

    public List<Servico> execute() {
        log.info("Obtendo todos serviços");
        return repository.listarServicos(true);
    }

}
