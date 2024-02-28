package com.gasfgrv.barbearia.adapter.servico.mapper;

import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoForm;
import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoResponse;
import com.gasfgrv.barbearia.adapter.servico.api.model.ServicosResponse;
import com.gasfgrv.barbearia.adapter.servico.database.ServicoEntity;
import com.gasfgrv.barbearia.domain.servico.model.Servico;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServicoMapper {

    private final ModelMapper mapper;

    public ServicoEntity paraEntidade(Servico servico) {
        return mapper.map(servico, ServicoEntity.class);
    }

    public Servico paraDominio(ServicoEntity servicoEntity) {
        return mapper.map(servicoEntity, Servico.class);
    }

    public Servico paraDominio(ServicoForm form) {
        return mapper.map(form, Servico.class);
    }

    public ServicoResponse paraResposta(Servico servico) {
        return mapper.map(servico, ServicoResponse.class);
    }

    public List<ServicosResponse> paraResposta(List<Servico> servicos) {
        return servicos.stream()
                .map(servico -> mapper.map(servico, ServicosResponse.class))
                .toList();
    }
}
