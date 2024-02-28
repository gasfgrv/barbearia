package com.gasfgrv.barbearia.domain.servico.exception;

public class ServicoNaoEncontradoException extends ServicoException {
    public ServicoNaoEncontradoException() {
        super("Serviço não encontrado");
    }
}
