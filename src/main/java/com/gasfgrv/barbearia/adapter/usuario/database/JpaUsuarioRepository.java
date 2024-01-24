package com.gasfgrv.barbearia.adapter.usuario.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    Optional<UsuarioEntity> findByLogin(String login);
}
