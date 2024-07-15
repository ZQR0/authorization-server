package com.testing.auth_server.common.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AuthorizationServerPropertyTests {

    @Autowired
    private AuthorizationServerProperty authorizationServerProperty;

    @Test
    void issuerUriIsNotNull() {
        assertThat(this.authorizationServerProperty.getIssuerUri()).isNotNull();
        System.out.println(this.authorizationServerProperty.getIssuerUri());
    }

    @Test
    void authenticationSuccessUrlIsNotNull() {
        assertThat(this.authorizationServerProperty.getAuthenticationSuccessUrl()).isNotNull();
    }

    @Test
    void savedRequestUrlStartsWithParamIsNotNull() {
        assertThat(this.authorizationServerProperty.getSavedRequestUrlStartsWith()).isNotNull();
    }

    @Test
    void customHandlerHeaderNameIsNotNull() {
        assertThat(this.authorizationServerProperty.getCustomHandlerHeaderName()).isNotNull();
    }
}
