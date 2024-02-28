package com.gasfgrv.barbearia.adapter.servico.adapter;

import com.gasfgrv.barbearia.adapter.servico.database.ServicoJpaRepository;
import com.gasfgrv.barbearia.adapter.servico.mapper.ServicoMapper;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ServicoRepositoryAdapter implements ServicoRepository {

    private final ServicoJpaRepository repository;
    private final ServicoMapper mapper;

    @Override
    public Servico novoServico(Servico servico) {
        return salvarEntidade(servico);
    }

    @Override
    public List<Servico> listarServicos(boolean apenasAtivos) {
        var servicos = apenasAtivos
                ? repository.findByAtivoTrue()
                : repository.findAll();

        return servicos.stream()
                .map(mapper::paraDominio)
                .toList();
    }

    @Override
    public Servico detalharServico(UUID idServico) {
        return repository.findById(idServico)
                .map(mapper::paraDominio)
                .orElse(null);
    }

    @Override
    public Servico atualizarServico(UUID idServico, Servico servico) {
        return salvarEntidade(servico);
    }


    @Override
    public void desativarServico(UUID idServico) {
        repository.findById(idServico)
                .ifPresent(servico -> {
                    servico.setAtivo(false);
                    salvarEntidade(mapper.paraDominio(servico));
                });
    }

    private Servico salvarEntidade(Servico servico) {
        var servicoEntity = mapper.paraEntidade(servico);
        return mapper.paraDominio(repository.save(servicoEntity));
    }

}
