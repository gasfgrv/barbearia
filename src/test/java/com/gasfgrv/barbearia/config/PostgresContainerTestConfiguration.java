package com.gasfgrv.barbearia.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class PostgresContainerTestConfiguration {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer() {
        DockerImageName dockerImage = DockerImageName.parse("postgres:latest");
        return new PostgreSQLContainer<>(dockerImage)
                .withDatabaseName("barbearia")
                .withPassword("postgres")
                .withUsername("postgres")
                .withReuse(true);
    }

}
