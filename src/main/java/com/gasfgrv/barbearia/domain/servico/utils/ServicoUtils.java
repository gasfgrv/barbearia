package com.gasfgrv.barbearia.domain.servico.utils;

import com.gasfgrv.barbearia.domain.servico.model.Servico;

import java.util.List;
import java.util.Set;

public class ServicoUtils {

    public static boolean existemServicosSemelhantes(List<Servico> servicos, String nome) {
        var palavrasChave = Set.of(nome.split("\\s"));
        return servicos.stream()
                .map(Servico::getNome)
                .anyMatch(nomeServico -> nomeDoServicoContemPalavrasChave(palavrasChave, nomeServico));
    }

    private static boolean nomeDoServicoContemPalavrasChave(Set<String> palavrasChave, String nomeServico) {
        return palavrasChave.stream().allMatch(nomeServico::contains);
    }

    public static String formataTexto(String texto) {
        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
    }

}
