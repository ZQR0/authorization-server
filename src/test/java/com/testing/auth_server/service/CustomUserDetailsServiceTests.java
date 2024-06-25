package com.testing.auth_server.service;

import com.testing.auth_server.service.details.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CustomUserDetailsServiceTests {

    private static final String TEST_EMAIL_USER = "user@gmail.com";
    private static final String TEST_EMAIL_ADMIN = "admin@gmail.com";

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserIsNotNull() {
        assertThat(this.customUserDetailsService.loadUserByUsername(TEST_EMAIL_USER)).isNotNull();
    }

    @Test
    void loadAdminIsNotNull() {
        assertThat(this.customUserDetailsService.loadUserByUsername(TEST_EMAIL_ADMIN)).isNotNull();
    }

}
