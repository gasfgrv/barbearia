package com.gasfgrv.barbearia.adapter.usuario.api;

import com.gasfgrv.barbearia.adapter.usuario.api.model.DadosAutenticacao;
import com.gasfgrv.barbearia.adapter.usuario.api.model.TokenJwtResponse;
import com.gasfgrv.barbearia.adapter.usuario.mapper.UsuarioMapper;
import com.gasfgrv.barbearia.usecase.usuario.AutenticarUsuarioUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login")
@RequiredArgsConstructor
public class AutenticacaoUsuarioController {
    private final AutenticarUsuarioUseCase autenticarUsuario;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper mapper;

    @PostMapping
    ResponseEntity<TokenJwtResponse> tratarRequisicao(@RequestBody @Valid DadosAutenticacao dadosAutenticacao,
                                                      HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.getEmail(), dadosAutenticacao.getSenha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var principal = (UserDetails) authentication.getPrincipal();
        var token = autenticarUsuario.autenticar(mapper.paraDominio(principal));
        return ResponseEntity.ok(new TokenJwtResponse(token));
    }
}
