package com.gasfgrv.barbearia.adapter.usuario.mapper;

import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.usuario.model.UsuarioMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Test
    void paraDominio() {
        var usuario = usuarioMapper.paraDominio(UsuarioMock.getBarbeiroEntity());
        Assertions
                .assertThat(usuario)
                .isExactlyInstanceOf(Usuario.class)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getBarbeiro());
    }

    @Test
    void paraEntidade() {
        var usuario = usuarioMapper.paraEntidade(UsuarioMock.getCliente());
        Assertions
                .assertThat(usuario)
                .isExactlyInstanceOf(UsuarioEntity.class)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getClienteEntity());
    }
}