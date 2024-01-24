package com.gasfgrv.barbearia.infra.beans;

import com.gasfgrv.barbearia.domain.perfil.port.PerfilRepository;
import com.gasfgrv.barbearia.domain.usuario.port.TokenService;
import com.gasfgrv.barbearia.domain.usuario.port.UsuarioRepository;
import com.gasfgrv.barbearia.usecase.perfil.ObterPerfilUseCase;
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

//    @Bean
//    public AtualizarDadosServicoUseCase atualizarDadosServicoUseCase(ServicoRepository repository) {
//        return new AtualizarDadosServicoUseCase(repository);
//    }
//
//    @Bean
//    public DesativarServicoUseCase desativarServicoUseCase(ServicoRepository repository) {
//        return new DesativarServicoUseCase(repository);
//    }
//
//    @Bean
//    public ListarServicosAtivosUseCase listarServicosAtivosUseCase(ServicoRepository repository) {
//        return new ListarServicosAtivosUseCase(repository);
//    }
//
//    @Bean
//    public ObterDadosDoServicoUseCase obterDadosDoServicoUseCase(ServicoRepository repository) {
//        return new ObterDadosDoServicoUseCase(repository);
//    }
//
//    @Bean
//    public SalvarNovoServicoUseCase novoServicoUseCase(ServicoRepository repository) {
//        return new SalvarNovoServicoUseCase(repository);
//    }
}