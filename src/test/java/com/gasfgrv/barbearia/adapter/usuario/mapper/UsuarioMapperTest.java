package com.gasfgrv.barbearia.adapter.usuario.mapper;

import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Test
    void paraDominio() {
        var usuario = usuarioMapper.paraDominio(UsuarioMock.getBarbeiroEntity());

        assertThat(usuario)
                .isExactlyInstanceOf(Usuario.class)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getBarbeiro());
    }

    @Test
    void paraEntidade() {
        var usuario = usuarioMapper.paraEntidade(UsuarioMock.getCliente());

        assertThat(usuario)
                .isExactlyInstanceOf(UsuarioEntity.class)
                .usingRecursiveComparison()
                .isEqualTo(UsuarioMock.getClienteEntity());
    }

}
