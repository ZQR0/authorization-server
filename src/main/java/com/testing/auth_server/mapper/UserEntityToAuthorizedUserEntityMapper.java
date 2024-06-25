package com.testing.auth_server.mapper;

import com.testing.auth_server.config.security.details.AuthorizedUserDetails;
import com.testing.auth_server.dao.entity.AuthorityEntity;
import com.testing.auth_server.dao.entity.RoleEntity;
import com.testing.auth_server.dao.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserEntityToAuthorizedUserEntityMapper implements Mapper<UserEntity, AuthorizedUserDetails> {


    @Override
    public AuthorizedUserDetails map(UserEntity entity) {
        return AuthorizedUserDetails.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPasswordHash())
                .birthday(entity.getBirthday())
                .isEnabled(entity.getActive())
                .isAccountNonExpired(entity.getActive())
                .isAccountNonLocked(entity.getActive())
                .isCredentialsNonExpired(entity.getActive())
                .oauthAttributes(prepareOAuthAttributes())
                .authorities(prepareAuthorities(entity))
                .build();
    }

    // TODO Заполнить нужными полями, если будет необходимо
    private static Map<String, Object> prepareOAuthAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        return attributes;
    }

    private static Set<? extends GrantedAuthority> prepareAuthorities(UserEntity entity) {
        return entity.getRoles().stream()
                .filter(RoleEntity::getActive)
                .flatMap(role -> role.getAuthorities().stream())
                .filter(AuthorityEntity::getActive)
                .map(authority -> new SimpleGrantedAuthority(authority.getCode()))
                .collect(Collectors.toSet());
    }

}
