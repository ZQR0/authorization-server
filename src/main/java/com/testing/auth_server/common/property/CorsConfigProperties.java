package com.testing.auth_server.common.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * All fields for CORS config in WebSecurity
 * from application.yml properties
 * */
@Configuration
@ConfigurationProperties(prefix = "spring.mvc.cors.configs")
@Getter
@Setter
public class CorsConfigProperties {

    private String pattern;
    private String allowedOrigins;
    private String allowedHeaders;
    private String exposedHeaders;
    private String allowedMethods;
    private Boolean allowCredentials;

}
