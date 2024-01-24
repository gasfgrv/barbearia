package com.gasfgrv.barbearia.infra.security;

import lombok.Getter;

@Getter
public enum AuthorityEnum {
    BARBER("BARBEIRO"),
    CLIENT("CLIENTE");

    private final String value;

    AuthorityEnum(String value) {
        this.value = value;
    }
}
