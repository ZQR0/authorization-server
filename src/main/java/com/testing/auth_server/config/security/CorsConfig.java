package com.testing.auth_server.config.security;

import com.testing.auth_server.common.property.CorsConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CorsConfig {

    private final CorsConfigProperties corsProperties;

    @Bean
    public FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean() {
        log.debug("CORS Filter creation...");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(this.corsProperties.getAllowedOrigins());
        corsConfiguration.addAllowedHeader(this.corsProperties.getAllowedHeaders());
        corsConfiguration.addExposedHeader(this.corsProperties.getExposedHeaders());
        corsConfiguration.addAllowedMethod(this.corsProperties.getAllowedMethods());
        corsConfiguration.setAllowCredentials(this.corsProperties.getAllowCredentials());

        source.registerCorsConfiguration(this.corsProperties.getPattern(), corsConfiguration);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
