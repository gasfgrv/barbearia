package com.gasfgrv.barbearia.mocks.usuario.model;

import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.perfil.model.PerfilMock;

public class UsuarioMock {
    public static Usuario getCliente() {
        var cliente = new Usuario();
        cliente.setLogin("cliente@cliente.com");
        cliente.setSenha("123456");
        cliente.setPerfil(PerfilMock.getCliente());
        return cliente;
    }

    public static Usuario getBarbeiro() {
        var barbeiro = new Usuario();
        barbeiro.setLogin("barbeiro@teste.com");
        barbeiro.setSenha("123456");
        barbeiro.setPerfil(PerfilMock.getBarbeiro());
        return barbeiro;
    }

    public static UsuarioEntity getClienteEntity() {
        var cliente = new UsuarioEntity();
        cliente.setLogin("cliente@cliente.com");
        cliente.setSenha("123456");
        cliente.setPerfil(PerfilMock.getClienteEntity());
        return cliente;
    }

    public static UsuarioEntity getBarbeiroEntity() {
        var barbeiro = new UsuarioEntity();
        barbeiro.setLogin("barbeiro@teste.com");
        barbeiro.setSenha("123456");
        barbeiro.setPerfil(PerfilMock.getBarbeiroEntity());
        return barbeiro;
    }
}