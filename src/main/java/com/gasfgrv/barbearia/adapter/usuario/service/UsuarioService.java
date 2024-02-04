package com.gasfgrv.barbearia.adapter.usuario.service;

import com.gasfgrv.barbearia.adapter.usuario.mapper.UsuarioMapper;
import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(repository.buscarPorEmail(username))
                .map(mapper::paraEntidade)
                .orElseThrow(UsuarioNaoEncontradoException::new);
    }
}
