package com.gasfgrv.barbearia.domain.usuario.model;

import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
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
public class Usuario {
    @EqualsAndHashCode.Include
    private String login;
    private String senha;
    private Perfil perfil;
}
