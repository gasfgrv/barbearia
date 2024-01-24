package com.gasfgrv.barbearia.adapter.perfil.adapter;

import com.gasfgrv.barbearia.adapter.perfil.database.JpaPerfilRepository;
import com.gasfgrv.barbearia.adapter.perfil.mapper.PerfilMapper;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PerfilRepositoryAdapter implements PerfilRepository {
    private final JpaPerfilRepository repository;
    private final PerfilMapper mapper;

    @Override
    public Perfil buscarPorNome(String nome) {
        log.info("Buscando dados do perfil");
        return repository.findByNome(nome)
                .map(mapper::paraDominio)
                .orElse(null);
    }
}
