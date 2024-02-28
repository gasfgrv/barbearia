package com.gasfgrv.barbearia.infra.beans;

import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import com.gasfgrv.barbearia.domain.servico.port.ServicoRepository;
import com.gasfgrv.barbearia.domain.usuario.port.TokenService;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import com.gasfgrv.barbearia.usecase.perfil.ObterPerfilUseCase;
import com.gasfgrv.barbearia.usecase.servico.AtualizarServicoUseCase;
import com.gasfgrv.barbearia.usecase.servico.DesativarServicoUseCase;
import com.gasfgrv.barbearia.usecase.servico.DetalharServicoUseCase;
import com.gasfgrv.barbearia.usecase.servico.ListarServicosUseCase;
import com.gasfgrv.barbearia.usecase.servico.NovoServicoUseCase;
import com.gasfgrv.barbearia.usecase.usuario.AlterarSenhaUseCase;
import com.gasfgrv.barbearia.usecase.usuario.AutenticarUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesBeans {
    @Bean
    public ObterPerfilUseCase obterPerfilUseCase(PerfilRepository repository) {
        return new ObterPerfilUseCase(repository);
    }

    @Bean
    public AutenticarUsuarioUseCase autenticarUsuarioUseCase(TokenService tokenService) {
        return new AutenticarUsuarioUseCase(tokenService);
    }

    @Bean
    public AlterarSenhaUseCase recuperarSenhaUseCase(UsuarioRepository repository) {
        return new AlterarSenhaUseCase(repository);
    }

    @Bean
    public AtualizarServicoUseCase atualizarDadosServicoUseCase(ServicoRepository repository) {
        return new AtualizarServicoUseCase(repository);
    }

    @Bean
    public DesativarServicoUseCase desativarServicoUseCase(ServicoRepository repository) {
        return new DesativarServicoUseCase(repository);
    }

    @Bean
    public ListarServicosUseCase listarServicosAtivosUseCase(ServicoRepository repository) {
        return new ListarServicosUseCase(repository);
    }

    @Bean
    public DetalharServicoUseCase obterDadosDoServicoUseCase(ServicoRepository repository) {
        return new DetalharServicoUseCase(repository);
    }

    @Bean
    public NovoServicoUseCase novoServicoUseCase(ServicoRepository repository) {
        return new NovoServicoUseCase(repository);
    }

}