package com.gasfgrv.barbearia.adapter.usuario.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlterarSenhaForm implements Serializable {
    @Email(message = "Informar um e-mail válido")
    private String emailLogin;

    @NotBlank(message = "Informar uma nova senha")
    private String novaSenha;
}
