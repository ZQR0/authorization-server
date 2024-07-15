package com.testing.auth_server.config.security.utils;

import com.testing.auth_server.dao.entity.AuthorityEntity;
import com.testing.auth_server.dao.entity.RoleEntity;
import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Данный класс используется для дополнения стандартного токена JWT,
 * который предоставляется JWKSource.
 * В токен добавляется email, password_hash и authorities (scope)
 * */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenGenerationUtils {

    private final UserService userService;

    public Map<String, Object> prepareClaims(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = this.userService.findByEmail(email);
        } catch (Exception exception) {
            log.error(String.format("User with email %s not found", email));
        }

        // Такой вариант маловероятен, т.к. JWT код генерируется на основе
        // authorization code, который даётся при вводе валидного email
        if (userEntity == null) {
            log.warn("User is null (HOW ???)");
            throw new NullPointerException("User is null");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("password_hash", userEntity.getPasswordHash());
        result.put("authorities", prepareAuthorities(userEntity));

        return result;
    }


    private static Set<String> prepareAuthorities(UserEntity entity) {
        return entity.getRoles().stream()
                .filter(RoleEntity::getActive)
                .flatMap(role -> role.getAuthorities().stream())
                .filter(AuthorityEntity::getActive)
                .map(AuthorityEntity::getCode)
                .collect(Collectors.toSet());
    }

}
