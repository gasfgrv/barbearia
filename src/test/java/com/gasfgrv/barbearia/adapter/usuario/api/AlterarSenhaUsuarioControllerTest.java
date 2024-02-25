package com.gasfgrv.barbearia.adapter.usuario.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gasfgrv.barbearia.adapter.usuario.api.model.AlterarSenhaEmailForm;
import com.gasfgrv.barbearia.adapter.usuario.api.model.AlterarSenhaForm;
import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.adapter.usuario.database.UsuarioEntity;
import com.gasfgrv.barbearia.config.PostgresContainerTestConfiguration;
import com.gasfgrv.barbearia.mocks.usuario.UsuarioMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
@Import(PostgresContainerTestConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AlterarSenhaUsuarioControllerTest {

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
        RestAssured.basePath = "/v1/login/senhas";

        BARBEIRO.setSenha(passwordEncoder.encode(BARBEIRO.getSenha()));
        repository.save(BARBEIRO);
    }

    @Test
    void tratarRequisicaoDeNotificacaoViaEmailComSucesso(CapturedOutput output) throws JsonProcessingException {
        var barbeiro = UsuarioMock.getBarbeiro();

        var form = new AlterarSenhaEmailForm();
        form.setEmail(barbeiro.getLogin());

        RestAssured
                .given().log().everything()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(corpoDaRequisicaoJson(form))
                .when().log().everything()
                .post()
                .then().log().everything()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        assertThat(output).contains("[POST] /v1/login/senhas - Requisição recebida");
    }

    @Test
    void tratarRequisicaoDeNotificacaoViaEmailParaUsuarioNaoEncontrado(CapturedOutput output) throws JsonProcessingException {
        var cliente = UsuarioMock.getCliente();

        var form = new AlterarSenhaEmailForm();
        form.setEmail(cliente.getLogin().replaceAll("@","__"));

        RestAssured
                .given().log().everything()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(corpoDaRequisicaoJson(form))
                .when().log().everything()
                .post()
                .then().log().everything()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());

        assertThat(output).contains("Envio do email cancelado");
    }

    @Test
    void tratarRequisicaoParaAlterarSenha(CapturedOutput output) throws JsonProcessingException {
        var barbeiro = UsuarioMock.getBarbeiro();

        var form = new AlterarSenhaForm();
        form.setEmailLogin(barbeiro.getLogin());
        form.setNovaSenha("novaSenha");

        var senhaAntiga = obterSenha(form.getEmailLogin());

        RestAssured
                .given().log().everything()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(corpoDaRequisicaoJson(form))
                .when().log().everything()
                .put()
                .then().log().everything()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        var senha = obterSenha(form.getEmailLogin());

        assertThat(output).contains("[PUT] /v1/login/senhas - Requisição recebida");
        assertThat(senha).isNotEqualTo(senhaAntiga);
    }

    @Test
    void tratarRequisicaoParaAlterarSenhaComUsuarioNaoEncontrado(CapturedOutput output) throws JsonProcessingException {
        var cliente = UsuarioMock.getCliente();

        var form = new AlterarSenhaForm();
        form.setEmailLogin(cliente.getLogin().replaceAll("@","__"));
        form.setNovaSenha("novaSenha");

        var senhaAntiga = obterSenha(form.getEmailLogin());

        RestAssured
                .given().log().everything()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(corpoDaRequisicaoJson(form))
                .when().log().everything()
                .put()
                .then().log().everything()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        var senha = obterSenha(form.getEmailLogin());

        assertThat(output).contains("[PUT] /v1/login/senhas - Requisição recebida");
        assertThat(output).contains("Envio do email cancelado");
        assertThat(senha).isEqualTo(senhaAntiga);
    }

    private String obterSenha(String login) {
        return repository.findByLogin(login)
                .map(UsuarioEntity::getSenha)
                .orElse(null);
    }

    private String corpoDaRequisicaoJson(Object body) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(body);
    }
}