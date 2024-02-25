package com.gasfgrv.barbearia.infra.exception.handler;

import com.gasfgrv.barbearia.domain.perfil.exception.PerfilException;
import com.gasfgrv.barbearia.domain.perfil.exception.PerfilNaoEncontradoException;
import com.gasfgrv.barbearia.domain.servico.exception.ServicoException;
import com.gasfgrv.barbearia.domain.servico.exception.ServicoExistenteException;
import com.gasfgrv.barbearia.domain.servico.exception.ServicoNaoEncontradoException;
import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioException;
import com.gasfgrv.barbearia.domain.usuario.exception.UsuarioNaoEncontradoException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.WWW_AUTHENTICATE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PerfilException.class)
    @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handlePerfilException(PerfilException ex, WebRequest request) {
        var status = INTERNAL_SERVER_ERROR;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }

    @ExceptionHandler(PerfilNaoEncontradoException.class)
    @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handlePerfilNaoEncontradoException(PerfilNaoEncontradoException ex, WebRequest request) {
        var status = INTERNAL_SERVER_ERROR;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }


    @ExceptionHandler(UsuarioException.class)
    @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleUsuarioException(UsuarioException ex, WebRequest request) {
        var status = INTERNAL_SERVER_ERROR;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex, WebRequest request) {
        var status = UNAUTHORIZED;
        var headers = setHttpHeaders();
        headers.put(WWW_AUTHENTICATE, Collections.singletonList("Bearer"));
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ApiResponse(responseCode = "401", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        var status = UNAUTHORIZED;
        var headers = setHttpHeaders();
        headers.put(WWW_AUTHENTICATE, Collections.singletonList("Bearer"));
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(ServicoException.class)
    @ApiResponse(responseCode = "500", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleServicoException(ServicoException ex, WebRequest request) {
        var status = INTERNAL_SERVER_ERROR;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }

    @ExceptionHandler(ServicoExistenteException.class)
    @ApiResponse(responseCode = "422", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleServicoExistenteException(ServicoExistenteException ex, WebRequest request) {
        var status = UNPROCESSABLE_ENTITY;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }

    @ExceptionHandler(ServicoNaoEncontradoException.class)
    @ApiResponse(responseCode = "404", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problema.class)))
    public ResponseEntity<Object> handleServicoNaoEncontradoException(ServicoNaoEncontradoException ex, WebRequest request) {
        var status = NOT_FOUND;
        var problema = Problema.builder()
                .status(status.value())
                .dataHora(OffsetDateTime.now())
                .titulo(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, problema, setHttpHeaders(), status, request);
    }

    private HttpHeaders setHttpHeaders() {
        var headers = new HttpHeaders();
        headers.put(CONTENT_TYPE, Collections.singletonList(APPLICATION_PROBLEM_JSON_VALUE));
        return headers;
    }

}