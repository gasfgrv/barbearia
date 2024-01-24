package com.gasfgrv.barbearia.adapter.usuario.adapter;

import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.adapter.usuario.mapper.UsuarioMapper;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository {
    private final JpaUsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public Usuario buscarPorEmail(String email) {
        log.info("Buscando usuário por login");
        return repository.findByLogin(email)
                .map(mapper::paraDominio)
                .orElse(null);
    }

    @Override
    public void salvarUsuario(Usuario usuario) {
        log.info("Salvando dados do usuário");
        UsuarioEntity entity = mapper.paraEntidade(usuario);
        repository.save(entity);
    }
}
