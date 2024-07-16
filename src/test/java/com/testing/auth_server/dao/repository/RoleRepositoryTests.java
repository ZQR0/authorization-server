package com.testing.auth_server.dao.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void defaultRoleIsNotNull() {
        assertThat(this.roleRepository.getDefaultRole()).isNotNull();
    }

}
