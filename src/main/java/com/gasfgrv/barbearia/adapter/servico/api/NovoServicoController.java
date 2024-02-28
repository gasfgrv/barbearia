package com.gasfgrv.barbearia.adapter.servico.api;

import com.gasfgrv.barbearia.adapter.servico.api.model.impl.NovoServicoForm;
import com.gasfgrv.barbearia.adapter.servico.api.model.ServicoResponse;
import com.gasfgrv.barbearia.adapter.servico.mapper.ServicoMapper;
import com.gasfgrv.barbearia.usecase.servico.NovoServicoUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/v1/servicos")
@Tag(name = "Serviços")
@RequiredArgsConstructor
public class NovoServicoController {

    private final NovoServicoUseCase novoServicoUseCase;
    private final ServicoMapper mapper;

    @PostMapping
    public ResponseEntity<ServicoResponse> tratarRequisicao(@RequestBody @Valid NovoServicoForm form,
                                                            HttpServletRequest httpServletRequest) {
        log.info("[{}] {} - Requisição recebida", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        var servico = mapper.paraDominio(form);
        var execute = novoServicoUseCase.execute(servico);
        var response = mapper.paraResposta(execute);

        var locationHeader = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id_servico}")
                .buildAndExpand(execute.getId())
                .toUri();

        return ResponseEntity
                .created(locationHeader)
                .body(response);
    }

}
