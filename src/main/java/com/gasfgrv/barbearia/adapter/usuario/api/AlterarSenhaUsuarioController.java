package com.gasfgrv.barbearia.adapter.usuario.api;

import com.gasfgrv.barbearia.adapter.usuario.api.model.AlterarSenhaEmailForm;
import com.gasfgrv.barbearia.adapter.usuario.api.model.AlterarSenhaForm;
import com.gasfgrv.barbearia.adapter.usuario.service.EmailNotificationService;
import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import com.gasfgrv.barbearia.usecase.usuario.AlterarSenhaUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/login/senhas")
@Tag(name = "Login")
@RequiredArgsConstructor
public class AlterarSenhaUsuarioController {
    private final AlterarSenhaUseCase alterarSenhaUseCase;
    private final EmailNotificationService emailNotificationService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> tratarRequisicao(@RequestBody @Valid AlterarSenhaEmailForm form,
                                                 HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());

        try {
            alterarSenhaUseCase.execute(form.getEmail());
            emailNotificationService.enviarEmailParaAtualizarSenha(form.getEmail());
        } catch (UsuarioNaoEncontradoException e) {
            log.warn("Envio do email cancelado - {}", e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Void> tratarRequisicao(@RequestBody @Valid AlterarSenhaForm form,
                                                 HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        try {
            alterarSenhaUseCase.execute(form.getEmailLogin(), passwordEncoder.encode(form.getNovaSenha()));
            emailNotificationService.enviarEmailDeSenhaAtualizada(form.getEmailLogin());
        } catch (UsuarioNaoEncontradoException e) {
            log.warn("Envio do email cancelado - {}", e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
