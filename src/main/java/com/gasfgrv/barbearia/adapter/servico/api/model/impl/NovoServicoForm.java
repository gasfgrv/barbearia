package com.gasfgrv.barbearia.adapter.servico.api.model.impl;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoForm;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NovoServicoForm implements Serializable, ServicoForm {
    @NotBlank(message = "Informar um nome de serviço válido")
    private String nome;

    @DecimalMin(value = "1.0", message = "Informar um valor maior que R$1,00")
    @Digits(integer = 3, fraction = 2, message = "Informar o valor seguindo o seguinte padrão: ###,##")
    private BigDecimal preco;

    private String descricao;

    @Min(value = 10, message = "Informar um serviço com pelo menos 10 minutos")
    @Max(value = 60, message = "Informar um serviço com no máximo 60 minutos")
    private int duracao;
}
