package com.gasfgrv.barbearia.mocks.usuario;

import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.perfil.PerfilMock;

public class UsuarioMock {

    private static final String SENHA = "123456";
    private static final String EMAIL_CLIENTE = "cliente@cliente.com";
    private static final String EMAIL_BARBEIRO = "barbeiro@teste.com";

    public static Usuario getCliente() {
        var cliente = new Usuario();
        cliente.setLogin(EMAIL_CLIENTE);
        cliente.setSenha(SENHA);
        cliente.setPerfil(PerfilMock.getCliente());
        return cliente;
    }

    public static Usuario getBarbeiro() {
        var barbeiro = new Usuario();
        barbeiro.setLogin(EMAIL_BARBEIRO);
        barbeiro.setSenha(SENHA);
        barbeiro.setPerfil(PerfilMock.getBarbeiro());
        return barbeiro;
    }

    public static UsuarioEntity getClienteEntity() {
        var cliente = new UsuarioEntity();
        cliente.setLogin(EMAIL_CLIENTE);
        cliente.setSenha(SENHA);
        cliente.setPerfil(PerfilMock.getClienteEntity());
        return cliente;
    }

    public static UsuarioEntity getBarbeiroEntity() {
        var barbeiro = new UsuarioEntity();
        barbeiro.setLogin(EMAIL_BARBEIRO);
        barbeiro.setSenha(SENHA);
        barbeiro.setPerfil(PerfilMock.getBarbeiroEntity());
        return barbeiro;
    }

}
