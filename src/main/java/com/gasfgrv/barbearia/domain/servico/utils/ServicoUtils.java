package com.gasfgrv.barbearia.domain.servico.utils;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServicoUtils {

    public static boolean existemServicosSemelhantes(List<Servico> servicos, String nome) {
        if (servicos.isEmpty()) {
            return false;
        }

        return servicos.stream()
                .map(Servico::getNome)
                .allMatch(nomeServico -> nomeDoServicoContemPalavrasChave(nomeServico, nome.split("\\s")));
    }

    private static boolean nomeDoServicoContemPalavrasChave(String nomeServico, String... palavrasChave) {
        return Set.of(palavrasChave).stream().allMatch(nomeServico::contains);
    }

    public static String formataTexto(String texto) {
        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
    }

}
