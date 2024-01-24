package com.gasfgrv.barbearia.domain.usuario.port;

import com.gasfgrv.barbearia.domain.usuario.model.Usuario;

public interface TokenService {
    String gerarToken(Usuario usuario);

    String getSubject(String token);
}
