package com.gasfgrv.barbearia.usecase.usuario;

import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AutenticarUsuarioUseCase {
    private final TokenService tokenService;

    public String autenticar(Usuario usuario) {
        log.info("Gerando token para as credenciais do usuário");
        return tokenService.gerarToken(usuario);
    }
}
