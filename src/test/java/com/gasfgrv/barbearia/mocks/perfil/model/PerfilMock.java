package com.gasfgrv.barbearia.mocks.perfil.model;

import com.gasfgrv.barbearia.adapter.perfil.database.PerfilEntity;
import com.gasfgrv.barbearia.domain.perfil.model.Perfil;

public class PerfilMock {
    private enum perfilEnum {
        CLIENTE(1, "CLIENTE"),
        BARBEIRO(2, "BARBEIRO");

        private final int id;
        private final String permissao;

        perfilEnum(int id, String permissao) {
            this.id = id;
            this.permissao = permissao;
        }
    }

    public static Perfil getCliente() {
        var cliente = new Perfil();
        cliente.setId(perfilEnum.CLIENTE.id);
        cliente.setNome(perfilEnum.CLIENTE.permissao);
        return cliente;
    }

    public static Perfil getBarbeiro() {
        var barbeiro = new Perfil();
        barbeiro.setId(perfilEnum.BARBEIRO.id);
        barbeiro.setNome(perfilEnum.BARBEIRO.permissao);
        return barbeiro;
    }

    public static PerfilEntity getClienteEntity() {
        var entity = new PerfilEntity();
        entity.setId(perfilEnum.CLIENTE.id);
        entity.setNome(perfilEnum.CLIENTE.permissao);
        return entity;
    }

    public static PerfilEntity getBarbeiroEntity() {
        var entity = new PerfilEntity();
        entity.setId(perfilEnum.BARBEIRO.id);
        entity.setNome(perfilEnum.BARBEIRO.permissao);
        return entity;
    }
}