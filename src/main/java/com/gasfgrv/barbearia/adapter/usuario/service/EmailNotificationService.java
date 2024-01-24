package com.gasfgrv.barbearia.adapter.usuario.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService {
    private final JavaMailSender javaMailSender;

    @Async
    public void enviarEmailParaAtualizarSenha(String email) {
        log.info("Enviando email para: {}", email);
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Barbearia | Recuperação de senha");
        message.setText("Acesse o link para definir uma nova senha: http://localhost:4200/senhas/alterar");
        javaMailSender.send(message);
    }

    @Async
    public void enviarEmailDeSenhaAtualizada(String email) {
        log.info("Enviando email para: {}", email);
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Barbearia | Alteração de senha");
        message.setText("Senha alterada com sucesso");
        javaMailSender.send(message);
    }
}
