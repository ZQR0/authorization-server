package com.testing.auth_server.config.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация сервера ресурсов самого сервера авторизации
 * Описывает доступ к внутренним данным сервера
 * */
@Configuration
@RequiredArgsConstructor
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(@NonNull HttpSecurity httpSecurity) throws Exception {

        httpSecurity.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.oauth2ResourceServer((server) -> {
            server.jwt(Customizer.withDefaults());
        });

        return httpSecurity.build();
    }

}
