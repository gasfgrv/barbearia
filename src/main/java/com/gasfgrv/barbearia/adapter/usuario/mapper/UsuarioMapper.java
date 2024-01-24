package com.gasfgrv.barbearia.adapter.usuario.mapper;

import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {
    private final ModelMapper mapper;

    public UsuarioEntity paraEntidade(Usuario usuario) {
        return mapper.map(usuario, UsuarioEntity.class);
    }

    public Usuario paraDominio(UserDetails userDetails) {
        return mapper.map(userDetails, Usuario.class);
    }
}
