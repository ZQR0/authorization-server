package com.testing.auth_server.service.impl;

import com.testing.auth_server.common.exception.UserAlreadyExistsException;
import com.testing.auth_server.dao.entity.AuthorityEntity;
import com.testing.auth_server.dao.entity.RoleEntity;
import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.dao.repository.AuthorityRepository;
import com.testing.auth_server.dao.repository.RoleRepository;
import com.testing.auth_server.dao.repository.UserRepository;
import com.testing.auth_server.dto.UserRegistrationDto;
import com.testing.auth_server.service.UserRegistrationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Реализация {@link UserRegistrationService}
 * */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultUserRegistrationService implements UserRegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;


    @Override
    public void register(UserRegistrationDto dto) throws Exception {
        final String email = dto.getEmail();
        if (this.checkIsUserExists(email)) {
            throw new UserAlreadyExistsException(String.format("User with email %s already exists", email));
        }

        UserEntity user = UserEntity.builder()
                .email(email)
                .username(dto.getUsername())
                .passwordHash(this.passwordEncoder.encode(dto.getPassword()))
                .birthday(dto.getBirthday())
                .active(true)
                .roles(this.prepareDefaultRoles())
                .build();

        log.info(String.format("UserEntity with email %s built", dto.getEmail()));

        this.userRepository.save(user);
        log.info("User successfully saved");
    }


    private boolean checkIsUserExists(String email) {
        return this.userRepository.findByEmail(email).isPresent();
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
