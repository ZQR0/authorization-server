package com.testing.auth_server.dao.repository;

import com.testing.auth_server.dao.entity.OAuth2ClientEntity;
import com.testing.auth_server.mapper.OAuth2ClientEntityToRegisteredClientMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final OAuth2ClientRepository clientRepository;
    private final OAuth2ClientEntityToRegisteredClientMapper mapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void save(@NonNull RegisteredClient registeredClient) {
        OAuth2ClientEntity entity = OAuth2ClientEntity.builder()
                .clientId(registeredClient.getClientId())
                .clientSecret(registeredClient.getClientSecret())
                .clientName(registeredClient.getClientName())
                .clientSecretExpiresAt(LocalDateTime.from(registeredClient.getClientSecretExpiresAt()))
                .clientAuthenticationMethods(prepareClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods()))
                .authorizationGrantTypes(prepareAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes()))
                .redirectUris(prepareRedirectUris(registeredClient.getRedirectUris()))
                .scopes(prepareScopes(registeredClient.getScopes()))
                .build();

        this.clientRepository.save(entity);
        log.info(String.format("Client with client id %s saved", registeredClient.getClientId()));
    }

    @Override
    public RegisteredClient findById(String id) {
        OAuth2ClientEntity entity = this.clientRepository.findById(Long.parseLong(id))
                .orElse(null);

        if (entity == null) {
            log.warn("OAuth2 entity is null by id (Not found)");
            return null;
        }

        log.info(String.format("%s successfully found", entity.getClientId()));
        return this.mapper.map(entity);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        OAuth2ClientEntity entity = this.clientRepository.findByClientId(clientId)
                .orElse(null);

        if (entity == null) {
            log.warn("OAuth2 entity is null by clientId (Not found)");
            return null;
        }

        log.info(String.format("%s successfully found", entity.getClientId()));
        return this.mapper.map(entity);
    }

    private static String[] prepareClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
        return clientAuthenticationMethods.stream()
                .map(ClientAuthenticationMethod::toString)
                .toList()
                .toArray(new String[0]);
    }

    private static String[] prepareAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes) {
        return authorizationGrantTypes.stream()
                .map(AuthorizationGrantType::toString)
                .toList()
                .toArray(new String[0]);
    }

    private static String[] prepareRedirectUris(Set<String> redirectUris) {
        return redirectUris.stream()
                .toList()
                .toArray(new String[0]);
    }

    private static String[] prepareScopes(Set<String> scopes) {
        return scopes.stream()
                .toList()
                .toArray(new String[0]);
    }
}
