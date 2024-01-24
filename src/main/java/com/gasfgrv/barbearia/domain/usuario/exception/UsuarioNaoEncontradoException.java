package com.gasfgrv.barbearia.domain.usuario.exception;

public class UsuarioNaoEncontradoException extends UsuarioException {
    public UsuarioNaoEncontradoException() {
        super("Usuário não foi encontrado");
    }
}
