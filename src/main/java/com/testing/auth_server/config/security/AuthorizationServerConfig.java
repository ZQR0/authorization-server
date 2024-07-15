package com.testing.auth_server.config.security;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.testing.auth_server.common.constant.SecurityConstants;
import com.testing.auth_server.common.property.AuthorizationServerProperty;
import com.testing.auth_server.config.security.utils.JwtTokenGenerationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Map;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthorizationServerConfig {

    private final AuthorizationServerProperty authorizationServerProperty;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerationUtils tokenGenerationUtils;


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer oAuth2AuthorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        http.exceptionHandling((exc) -> {
                    exc.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(SecurityConstants.LOGIN_PAGE));
                });

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .issuer(this.authorizationServerProperty.getIssuerUri())
                .tokenEndpoint(SecurityConstants.TEMP_OAUTH2_TOKEN_ENDPOINT)
                .build();
    }


    // Для токена нужно:
    // email, password hash, authorities (scope)
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
                    String email = claims.get("sub").toString(); // Получаем email через sub
                    if (email != null && email.contains("@")) {
                        Map<String, Object> specialClaims = tokenGenerationUtils.prepareClaims(email);
                        claims.putAll(specialClaims);
                        log.debug("JWT-token generated successfully");
                    } else {
                        log.error("Email for JWT generation is null");
                    }
                });
            }
        };
    }


}
