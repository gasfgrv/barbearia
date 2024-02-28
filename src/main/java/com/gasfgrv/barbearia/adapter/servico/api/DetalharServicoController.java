package com.gasfgrv.barbearia.adapter.servico.api;

import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoResponse;
import com.gasfgrv.barbearia.adapter.servico.mapper.ServicoMapper;
import com.gasfgrv.barbearia.usecase.servico.DetalharServicoUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/servicos/{id_servico}")
@Tag(name = "Serviços")
@RequiredArgsConstructor
public class DetalharServicoController {

    private final DetalharServicoUseCase detalharServicoUseCase;
    private final ServicoMapper mapper;

    @GetMapping
    public ResponseEntity<ServicoResponse> tratarRequisicao(@PathVariable("id_servico") UUID idServico,
                                                            HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        var servico = detalharServicoUseCase.execute(idServico);
        var response = mapper.paraResposta(servico);
        return ResponseEntity.ok(response);
    }

}
