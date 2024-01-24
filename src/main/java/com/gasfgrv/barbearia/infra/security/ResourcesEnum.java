package com.gasfgrv.barbearia.infra.security;

import lombok.Getter;

@Getter
public enum ResourcesEnum {
    LOGIN("/v1/login"),
    CLIENTS("/v1/clientes"),
    BARBERS("/v1/barbeiros"),
    PASSWORDS("/v1/login/senhas"),
    SERVICES("v1/servicos/**");

    private final String value;

    ResourcesEnum(String value) {
        this.value = value;
    }
}
