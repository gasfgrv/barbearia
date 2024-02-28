package com.gasfgrv.barbearia.domain.servico.exception;

public class ServicoExistenteException extends ServicoException {
    public ServicoExistenteException() {
        super("Não foi possível inserir este serviço, já existe um semelhante");
    }
}
