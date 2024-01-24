package com.gasfgrv.barbearia;

import com.gasfgrv.barbearia.config.TestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BarbeariaApplicationTests {
    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context.containsBean("modelMapper")).isTrue();
    }
}
