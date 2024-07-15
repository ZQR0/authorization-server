package com.testing.auth_server.mapper;

import com.testing.auth_server.dao.entity.OAuth2ClientEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.UUID;

@Component
public class OAuth2ClientEntityToRegisteredClientMapper implements Mapper<OAuth2ClientEntity, RegisteredClient> {

    @Override
    public RegisteredClient map(OAuth2ClientEntity entity) {
        return RegisteredClient.withId(entity.getInSystemClientId().toString())
                .clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .clientName(entity.getClientName())
                .clientSecretExpiresAt(entity.getClientSecretExpiresAt().toInstant(ZoneOffset.UTC))
                .clientAuthenticationMethods(methods -> {
                    methods.addAll(entity.getClientAuthenticationMethods());
                })
                .authorizationGrantTypes(types -> {
                    types.addAll(entity.getAuthorizationGrantTypes());
                })
                .redirectUris(uris -> uris.addAll(entity.getRedirectUris()))
                .scopes(scope -> scope.addAll(entity.getScopes()))
                .build();
    }
}
