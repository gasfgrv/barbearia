package com.gasfgrv.barbearia.adapter.usuario.service;

import com.gasfgrv.barbearia.adapter.usuario.database.JpaUsuarioRepository;
import com.gasfgrv.barbearia.config.IntegrationTestsBaseConfig;
import com.gasfgrv.barbearia.mocks.usuario.model.UsuarioMock;
import com.icegreen.greenmail.util.GreenMailUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.MessagingException;
import java.util.concurrent.TimeUnit;

class EmailNotificationServiceTest extends IntegrationTestsBaseConfig {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JpaUsuarioRepository repository;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @BeforeEach
    void setUp() {
        repository.save(UsuarioMock.getBarbeiroEntity());
    }

    @Test
    void enviarEmailParaAtualizarSenha() throws MessagingException, InterruptedException {
        var payload = "{ \"email\": \"%s\"}".formatted(UsuarioMock.getBarbeiroEntity().getLogin());
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(payload, headers);

        testRestTemplate.postForEntity("/v1/login/senhas", request, Void.class);
        executor.getThreadPoolExecutor().awaitTermination(1, TimeUnit.SECONDS);

        var receivedMessage = greenMail.getReceivedMessages()[0];

        Assertions
                .assertThat(receivedMessage.getAllRecipients())
                .hasSize(1);

        Assertions
                .assertThat(GreenMailUtil.getBody(receivedMessage))
                .contains("Acesse o link para definir uma nova senha: http://localhost:4200/senhas/alterar");

        Assertions
                .assertThat(receivedMessage.getSubject())
                .contains("Barbearia | Recuperação de senha");

        Assertions
                .assertThat(receivedMessage.getAllRecipients()[0].toString())
                .hasToString(UsuarioMock.getBarbeiroEntity().getLogin());
    }

    @Test
    void enviarEmailDeSenhaAtualizada() throws MessagingException, InterruptedException {
        var payload = "{ \"email_login\": \"%s\", \"nova_senha\": \"123456\"}".formatted(UsuarioMock.getBarbeiroEntity().getLogin());

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(payload, headers);

        testRestTemplate.put("/v1/login/senhas", request, Void.class);
        executor.getThreadPoolExecutor().awaitTermination(1, TimeUnit.SECONDS);

        var receivedMessage = greenMail.getReceivedMessages()[0];

        Assertions
                .assertThat(GreenMailUtil.getBody(receivedMessage))
                .contains("Senha alterada com sucesso");

        Assertions
                .assertThat(receivedMessage.getSubject())
                .contains("Barbearia | Alteração de senha");


        Assertions
                .assertThat(receivedMessage.getAllRecipients())
                .hasSize(1);

        Assertions
                .assertThat(receivedMessage.getAllRecipients()[0].toString())
                .hasToString(UsuarioMock.getBarbeiroEntity().getLogin());
    }
}