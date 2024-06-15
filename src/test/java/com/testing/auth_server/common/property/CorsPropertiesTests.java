package com.testing.auth_server.common.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CorsPropertiesTests {

    @Autowired
    private CorsConfigProperties corsProperties;

    @Test
    void patternIsNotNull() {
        assertThat(this.corsProperties.getPattern()).isNotNull();
        System.out.println(this.corsProperties.getPattern());
    }

    @Test
    void allowedOriginsIsNotNull() {
        assertThat(this.corsProperties.getAllowedOrigins()).isNotNull();
        System.out.println(this.corsProperties.getAllowedOrigins());
    }

    @Test
    void allowedHeadersAreNotNull() {
        assertThat(this.corsProperties.getAllowedHeaders()).isNotNull();
        System.out.println(this.corsProperties.getAllowedHeaders());
    }

    @Test
    void exposedHeadersAreNotNull() {
        assertThat(this.corsProperties.getExposedHeaders()).isNotNull();
        System.out.println(this.corsProperties.getExposedHeaders());
    }

    @Test
    void allowedMethodsAreNotNull() {
        assertThat(this.corsProperties.getAllowedMethods()).isNotNull();
        System.out.println(this.corsProperties.getAllowedMethods());
    }

    @Test
    void allowCredentialsIsNotNull() {
        assertThat(this.corsProperties.getAllowCredentials()).isNotNull();
        System.out.println(this.corsProperties.getAllowCredentials());
    }


}
