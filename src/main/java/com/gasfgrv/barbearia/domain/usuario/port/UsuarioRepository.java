package com.gasfgrv.barbearia.domain.usuario.port;

import com.gasfgrv.barbearia.domain.usuario.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario buscarPorEmail(String email);

    void salvarUsuario(Usuario usuario);
}
