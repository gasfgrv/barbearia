package com.gasfgrv.barbearia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BarbeariaApplicationTests {
    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context.containsBean("modelMapper")).isTrue();
    }
}
