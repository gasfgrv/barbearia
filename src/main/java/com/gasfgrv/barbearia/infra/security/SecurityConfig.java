package com.gasfgrv.barbearia.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

import static com.gasfgrv.barbearia.infra.security.AuthorityEnum.BARBER;
import static com.gasfgrv.barbearia.infra.security.ResourcesEnum.BARBERS;
import static com.gasfgrv.barbearia.infra.security.ResourcesEnum.CLIENTS;
import static com.gasfgrv.barbearia.infra.security.ResourcesEnum.LOGIN;
import static com.gasfgrv.barbearia.infra.security.ResourcesEnum.PASSWORDS;
import static com.gasfgrv.barbearia.infra.security.ResourcesEnum.SERVICES;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorization -> authorization
                                .requestMatchers(GET, "/swagger-ui/**").permitAll()
                                .requestMatchers(GET, "/v3/api-docs/**").permitAll()
                                .requestMatchers(GET, "/actuator/**").permitAll()
                                .requestMatchers(POST, LOGIN.getValue()).permitAll()
                                .requestMatchers(POST, PASSWORDS.getValue()).permitAll()
                                .requestMatchers(PUT, PASSWORDS.getValue()).permitAll()
                                .requestMatchers(POST, CLIENTS.getValue()).permitAll()
                                .requestMatchers(POST, BARBERS.getValue()).permitAll()
                                .requestMatchers(GET, SERVICES.getValue()).hasAuthority(BARBER.getValue())
                                .requestMatchers(PUT, SERVICES.getValue()).hasAuthority(BARBER.getValue())
                                .requestMatchers(DELETE, SERVICES.getValue()).hasAuthority(BARBER.getValue())
                                .requestMatchers(POST, SERVICES.getValue()).hasAuthority(BARBER.getValue())
                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        var urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
