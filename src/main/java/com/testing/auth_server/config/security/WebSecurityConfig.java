package com.testing.auth_server.config.security;

import com.testing.auth_server.common.constant.SecurityConstants;
import com.testing.auth_server.service.details.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> {
            authz
                    .requestMatchers("/api/v1/users/**").permitAll()
                    .anyRequest().authenticated();
        });

        http.csrf(AbstractHttpConfigurer::disable);

        http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(this.customUserDetailsService)
                .passwordEncoder(this.passwordEncoder);


        http.formLogin(Customizer.withDefaults());

        // Logout конфигурация
        http.logout(configurer -> {
            configurer
                    .logoutUrl(SecurityConstants.LOGOUT_PAGE)
                    .clearAuthentication(true)
                    .logoutSuccessHandler((request, response, authentication) -> {
                        response.sendRedirect(SecurityConstants.LOGIN_PAGE);
                    });
        });

        return http.build();
    }

}
