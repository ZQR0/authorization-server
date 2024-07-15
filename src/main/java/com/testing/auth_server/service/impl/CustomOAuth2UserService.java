package com.testing.auth_server.service.impl;

import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.mapper.UserEntityToAuthorizedUserEntityMapper;
import com.testing.auth_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


/**
 * Данный класс является дополнением класса {@link DefaultOAuth2UserService},
 * который является аналогом {@link CustomUserDetailsService}
 * для OAuth 2.0
 * */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    private final UserEntityToAuthorizedUserEntityMapper mapper;

    // Логика работы следующая:
    // 1) получаем пользователя через базу данных по email, который получен из запроса
    // 2) проверяем существует ли пользователь
    // 3) возвращаем реализацию OAuth2User через кастомный маппер
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        UserEntity userEntity = null;
        try {
            userEntity = this.userService.findByEmail(oAuth2User.getName());
        } catch (Exception exception) {
            log.error("User entity is null");
        }

        return this.mapper.map(userEntity);
    }
}
