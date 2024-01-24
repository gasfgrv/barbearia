package com.gasfgrv.barbearia.usecase.usuario;

import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class AlterarSenhaUseCase {
    private final UsuarioRepository repository;

    public void execute(String email) {
        if (Optional.ofNullable(repository.buscarPorEmail(email)).isEmpty()) {
            log.warn("Usuário não foi encontrado");
            throw new UsuarioNaoEncontradoException();
        }
    }

    public void execute(String email, String senha) {
        var usuario = Optional.ofNullable(repository.buscarPorEmail(email));

        if (usuario.isEmpty()) {
            log.warn("Usuário não foi encontrado");
            throw new UsuarioNaoEncontradoException();
        }

        usuario.get().setSenha(senha);
        repository.salvarUsuario(usuario.get());
    }
}
