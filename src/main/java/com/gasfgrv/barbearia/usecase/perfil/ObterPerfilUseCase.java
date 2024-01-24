package com.gasfgrv.barbearia.usecase.perfil;

import com.gasfgrv.barbearia.domain.perfil.exception.PerfilNaoEncontradoException;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ObterPerfilUseCase {
    private final PerfilRepository repository;

    public Perfil obterPerfil(String nome) {
        return Optional.ofNullable(repository.buscarPorNome(nome.toUpperCase()))
                .orElseThrow(() -> {
                    log.error("Perfil não encontrado");
                    return new PerfilNaoEncontradoException("Perfil não encontrado");
                });
    }
}
