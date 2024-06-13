package com.testing.auth_server;

import com.testing.auth_server.common.property.AuthorizationServerProperty;
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
}
