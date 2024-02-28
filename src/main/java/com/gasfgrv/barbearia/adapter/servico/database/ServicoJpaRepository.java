package com.gasfgrv.barbearia.adapter.servico.database;

import com.gasfgrv.barbearia.domain.servico.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, UUID> {
    List<ServicoEntity> findByAtivoTrue();
}
