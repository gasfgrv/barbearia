package com.gasfgrv.barbearia.adapter.servico.api;

import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoResponse;
import com.gasfgrv.barbearia.adapter.servico.api.model.impl.AtualizarServicoForm;
import com.gasfgrv.barbearia.adapter.servico.mapper.ServicoMapper;
import com.gasfgrv.barbearia.usecase.servico.AtualizarServicoUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/servicos/{id_servico}")
@Tag(name = "Serviços")
@RequiredArgsConstructor
public class AtualizarServicoController {

    private final AtualizarServicoUseCase atualizarServicoUseCase;
    private final ServicoMapper mapper;

    @PutMapping
    public ResponseEntity<ServicoResponse> tratarRequisicao(@PathVariable("id_servico") UUID idServico,
                                                            @RequestBody @Valid AtualizarServicoForm form,
                                                            HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        var servico = mapper.paraDominio(form);
        var execute = atualizarServicoUseCase.execute(idServico, servico);
        var response = mapper.paraResposta(execute);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<ServicoResponse> tratarRequisicao(@PathVariable("id_servico") UUID idServico,
                                                            HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        atualizarServicoUseCase.execute(idServico);
        return ResponseEntity.noContent().build();
    }

}
