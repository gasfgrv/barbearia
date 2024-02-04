package com.gasfgrv.barbearia.adapter.perfil.mapper;

import com.gasfgrv.barbearia.domain.perfil.model.Perfil;
import com.gasfgrv.barbearia.mocks.perfil.PerfilMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PerfilMapperTest {

    @Autowired
    private PerfilMapper perfilMapper;

    @Test
    void paraDominio() {
        var perfil = perfilMapper.paraDominio(PerfilMock.getBarbeiroEntity());

        assertThat(perfil)
                .isExactlyInstanceOf(Perfil.class)
                .usingRecursiveComparison()
                .isEqualTo(PerfilMock.getBarbeiro());
    }

}
