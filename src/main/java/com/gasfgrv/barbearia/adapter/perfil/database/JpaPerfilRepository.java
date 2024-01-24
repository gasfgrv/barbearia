package com.gasfgrv.barbearia.adapter.perfil.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPerfilRepository extends JpaRepository<PerfilEntity, Integer> {
    Optional<PerfilEntity> findByNome(String nome);
}
