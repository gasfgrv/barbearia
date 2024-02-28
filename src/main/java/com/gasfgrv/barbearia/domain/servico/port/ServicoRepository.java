package com.gasfgrv.barbearia.domain.servico.port;

import com.gasfgrv.barbearia.domain.servico.model.Servico;

import java.util.List;
import java.util.UUID;

public interface ServicoRepository {
    Servico novoServico(Servico servico);

    List<Servico> listarServicos(boolean apenasAtivos);

    Servico detalharServico(UUID idServico);

    Servico atualizarServico(UUID idServico, Servico servico);

    void desativarServico(UUID idServico);
}
