package com.testing.auth_server.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserServiceApiTests {

    @Autowired
    private UserService userService;


    @Test
    void adminEntityIsNotNull( ) throws Exception {
        assertThat(this.userService.findByUsername("admin")).isNotNull();
    }


    @Test
    void userEntityIsNotNull( ) throws Exception {
        assertThat(this.userService.findByUsername("user")).isNotNull();
    }

}
