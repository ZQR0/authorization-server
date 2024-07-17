package com.testing.auth_server.service;

import com.testing.auth_server.dao.entity.AuthorityEntity;
import com.testing.auth_server.dao.entity.RoleEntity;
import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.dao.repository.AuthorityRepository;
import com.testing.auth_server.dao.repository.RoleRepository;
import com.testing.auth_server.service.impl.DefaultUserRegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserRegistrationServiceTests {

    @Autowired
    private DefaultUserRegistrationService userRegistrationService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testUserEntityBuildingProcess() {
        assertThat(this.buildUserEntity().getRoles()).isNotNull();
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder()
                .email("test@mail.com")
                .username("testname")
                .passwordHash(this.passwordEncoder.encode("12345678"))
                .birthday(LocalDate.now())
                .active(true)
                .roles(prepareDefaultRoles())
                .build();
    }

    private Set<RoleEntity> prepareDefaultRoles() {
        RoleEntity role = this.roleRepository.getDefaultRole();

        role.setAuthorities(prepareDefaultAuthorities());

        Set<RoleEntity> roleSet = new HashSet<>();
        roleSet.add(role);

        return roleSet;
    }

    private Set<AuthorityEntity> prepareDefaultAuthorities() {
        Set<AuthorityEntity> setOfAuthorities = new HashSet<>();
        AuthorityEntity readOwnAuthority = this.authorityRepository.getReadOwnDataAuthority();
        AuthorityEntity writeAuthority = this.authorityRepository.getWriteAuthority();
        setOfAuthorities.add(readOwnAuthority);
        setOfAuthorities.add(writeAuthority);

        return setOfAuthorities;
    }
}
