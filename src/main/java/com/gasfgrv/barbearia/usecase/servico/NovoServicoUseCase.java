package com.gasfgrv.barbearia.usecase.servico;

import com.gasfgrv.barbearia.domain.servico.exception.ServicoExistenteException;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.util.UUID;

import static com.gasfgrv.barbearia.domain.servico.utils.ServicoUtils.existemServicosSemelhantes;
import static com.gasfgrv.barbearia.domain.servico.utils.ServicoUtils.formataTexto;

@Slf4j
@RequiredArgsConstructor
public class NovoServicoUseCase {
    private final ServicoRepository repository;

    public Servico execute(Servico servico) {
        servico.setNome(formataTexto(servico.getNome()));

        log.info("Buscando por serviços semelhantes");
        if (existemServicosSemelhantes(repository.listarServicos(true), servico.getNome())) {
            throw new ServicoExistenteException();
        }

        servico.setPreco(servico.getPreco().setScale(2, RoundingMode.HALF_EVEN));
        servico.setDescricao(formataTexto(servico.getDescricao()));
        servico.setId(UUID.randomUUID());
        servico.setAtivo(true);

        log.info("Salvando o serviço");
        return repository.novoServico(servico);
    }


}
