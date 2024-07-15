package com.testing.auth_server.service.impl;


import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.mapper.UserEntityToAuthorizedUserEntityMapper;
import com.testing.auth_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserEntityToAuthorizedUserEntityMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = null;
        try {
            userEntity = this.userService.findByEmail(username);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        Assert.notNull(userEntity, "User Entity cannot be a null!!!");
        return this.mapper.map(userEntity);
    }
}
