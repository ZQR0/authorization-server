package com.testing.auth_server.common.property;


import lombok.Getter;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "spring.security.oauth2.authorizationserver")
@Configuration
@Getter
@Setter
public class AuthorizationServerProperty {
    private String issuerUri;
}
