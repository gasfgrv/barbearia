package com.gasfgrv.barbearia.adapter.usuario.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gasfgrv.barbearia.adapter.usuario.api.model.DadosAutenticacao;
import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.config.IntegrationTestsBaseConfig;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AutenticacaoUsuarioControllerTest extends IntegrationTestsBaseConfig {

    public static final UsuarioEntity BARBEIRO = UsuarioMock.getBarbeiroEntity();

    @LocalServerPort
    private int port;

    @Autowired
    private JpaUsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
        RestAssured.basePath = "/v1/login";

        BARBEIRO.setSenha(passwordEncoder.encode(BARBEIRO.getSenha()));
        repository.save(BARBEIRO);
    }

    @Test
    void tratarRequisicaoDeLoginComSucesso(CapturedOutput output) throws JsonProcessingException {
        RestAssured
                .given().log().everything()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosAutenticacaoJson())
                .when().log().everything()
                .post()
                .then().log().everything()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        assertThat(output).contains("[POST] /v1/login - Requisição recebida");
    }

    private String dadosAutenticacaoJson() throws JsonProcessingException {
        var barbeiro = UsuarioMock.getBarbeiro();

        var dadosAutenticacao = new DadosAutenticacao();
        dadosAutenticacao.setEmail(barbeiro.getLogin());
        dadosAutenticacao.setSenha(barbeiro.getSenha());

        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dadosAutenticacao);
    }
}