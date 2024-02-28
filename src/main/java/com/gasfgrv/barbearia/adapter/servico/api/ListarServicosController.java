package com.gasfgrv.barbearia.adapter.servico.api;

import com.gasfgrv.barbearia.adapter.servico.api.model.ServicosResponse;
import com.gasfgrv.barbearia.adapter.servico.mapper.ServicoMapper;
import com.gasfgrv.barbearia.usecase.servico.ListarServicosUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/servicos")
@Tag(name = "Serviços")
@RequiredArgsConstructor
public class ListarServicosController {

    private final ListarServicosUseCase listarServicosUseCase;
    private final ServicoMapper mapper;

    @GetMapping
    public ResponseEntity<List<ServicosResponse>> tratarRequisicao(HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        var servicos = listarServicosUseCase.execute();
        var response = mapper.paraResposta(servicos);
        return ResponseEntity.ok(response);
    }

}
