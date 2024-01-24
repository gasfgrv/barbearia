package com.gasfgrv.barbearia.adapter.perfil.mapper;

import com.gasfgrv.barbearia.adapter.perfil.database.PerfilEntity;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfilMapper {
    private final ModelMapper mapper;

    public Perfil paraDominio(PerfilEntity entity) {
        return mapper.map(entity, Perfil.class);
    }
}
