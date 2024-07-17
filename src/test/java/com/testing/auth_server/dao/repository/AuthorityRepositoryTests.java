package com.testing.auth_server.dao.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AuthorityRepositoryTests {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    void readOwnDataAuthorityIsNotNull() {
        assertThat(this.authorityRepository.getReadOwnDataAuthority()).isNotNull();
    }

    @Test
    void writeAuthorityIsNotNull() {
        assertThat(this.authorityRepository.getWriteAuthority()).isNotNull();
    }

    @Test
    void readOwnDataEquals() {
        assertThat(this.authorityRepository.getReadOwnDataAuthority().getCode()).isEqualTo("READ_OWN_DATA");
    }

    @Test
    void writeEquals() {
        assertThat(this.authorityRepository.getWriteAuthority().getCode()).isEqualTo("WRITE");
    }

}
