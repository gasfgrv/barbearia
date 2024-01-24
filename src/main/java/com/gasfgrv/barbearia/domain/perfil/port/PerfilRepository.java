package com.gasfgrv.barbearia.domain.perfil.port;

import com.gasfgrv.barbearia.domain.perfil.model.Perfil;

public interface PerfilRepository {
    Perfil buscarPorNome(String nome);
}
