package com.testing.auth_server.config.security;

import com.testing.auth_server.common.constant.SecurityConstants;
import com.testing.auth_server.common.property.AuthorizationServerProperty;
import com.testing.auth_server.config.security.handler.CustomAuthenticationSuccessHandler;
import com.testing.auth_server.service.impl.CustomUserDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.context.SecurityContextRepository;


/**
 * Конфигурация безопасности для самого приложения
 * Включает доступ к базе данных и админ-панели,
 * а также доступ к API-эндпоинтам
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthorizationServerProperty authorizationServerProperty;
    private final SecurityContextRepository securityContextRepository;

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> {
            authz
                    .requestMatchers(SecurityConstants.PERMITTED_URL_PATTERNS).permitAll()
                    .anyRequest().authenticated();
        });

        http.csrf(AbstractHttpConfigurer::disable);

        http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(this.customUserDetailsService)
                .passwordEncoder(this.passwordEncoder);

        http.securityContext(context -> context.securityContextRepository(this.securityContextRepository));

        http.formLogin(Customizer.withDefaults());
        // Для работы этой конфигурации нужен отдельный клиент
//        http.formLogin((configurer) -> {
//            configurer
//                    .loginPage(SecurityConstants.LOGIN_PAGE)
//                    .loginProcessingUrl(SecurityConstants.PROCESSING_LOGIN_PAGE_URL)
//                    .successHandler(this.authenticationSuccessHandler)
//                    .failureHandler(this.authenticationFailureHandler);
//        });

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

    @PostConstruct
    public void initHandlers() {
        // Init success handler
        this.authenticationSuccessHandler = new CustomAuthenticationSuccessHandler(
                this.authorizationServerProperty.getAuthenticationSuccessUrl(),
                this.authorizationServerProperty.getCustomHandlerHeaderName(),
                this.authorizationServerProperty.getSavedRequestUrlStartsWith()
        );

        // Init failure handler
        this.authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    }

}
