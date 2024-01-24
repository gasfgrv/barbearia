package com.gasfgrv.barbearia.domain.perfil.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Perfil {
    @EqualsAndHashCode.Include
    private int id;
    private String nome;
}
