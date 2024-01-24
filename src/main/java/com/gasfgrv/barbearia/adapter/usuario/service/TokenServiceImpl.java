package com.gasfgrv.barbearia.adapter.usuario.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gasfgrv.barbearia.adapter.usuario.mapper.UsuarioMapper;
import com.gasfgrv.barbearia.domain.usuario.model.Usuario;
import com.gasfgrv.barbearia.domain.usuario.port.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final UsuarioMapper mapper;

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String gerarToken(Usuario usuario) {
        var algoritmo = Algorithm.HMAC256(secret);
        var principal = mapper.paraEntidade(usuario);
        return JWT.create()
                .withIssuer("Login")
                .withSubject(principal.getUsername())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    }

    @Override
    public String getSubject(String token) {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("Login")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
