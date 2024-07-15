package com.testing.auth_server.dao.entity;

import com.testing.auth_server.dao.utils.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Репрезентация OAuth 2.0 клиента, который будет создаваться
 * при авторизации через клиент-приложение
 * */
@Entity(name = "oauth2_client_entity")
@Table(name = "oauth2_clients_table", schema = "auth_server")
@Getter
@Setter
@NoArgsConstructor
public class OAuth2ClientEntity extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "in_system_client_id", nullable = false)
    private Long inSystemClientId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_secret_expires_at", nullable = false)
    private LocalDateTime clientSecretExpiresAt;

    @Column(name = "client_authentication_methods", nullable = false, columnDefinition = "text[]")
    private String[] clientAuthenticationMethods;

    @Column(name = "authorization_grant_types", nullable = false, columnDefinition = "text[]")
    private String[] authorizationGrantTypes;

    @Column(name = "redirect_uris", nullable = false, columnDefinition = "text[]")
    private String[] redirectUris;

    @Column(name = "scopes", nullable = false, columnDefinition = "text[]")
    private String[] scopes;


    public OAuth2ClientEntity(
            String clientId,
            String clientSecret,
            String clientName,
            LocalDateTime clientSecretExpiresAt,
            String[] clientAuthenticationMethods,
            String[] authorizationGrantTypes,
            String[] redirectUris,
            String[] scopes
    )
    {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.clientSecretExpiresAt = clientSecretExpiresAt;
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        this.authorizationGrantTypes = authorizationGrantTypes;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
    }


    public Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
        if (checkCollection(this.clientAuthenticationMethods)) {
            return Collections.emptySet();
        }

        return Arrays.stream(this.clientAuthenticationMethods)
                .map(method -> new ClientAuthenticationMethod(method.trim()))
                .collect(Collectors.toSet());
    }


    public Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
        if (checkCollection(this.authorizationGrantTypes)) {
            return Collections.emptySet();
        }

        return Arrays.stream(this.authorizationGrantTypes)
                .map(type -> new AuthorizationGrantType(type.trim()))
                .collect(Collectors.toSet());
    }


    public Set<String> getRedirectUris() {
        if (checkCollection(this.redirectUris)) {
            return Collections.emptySet();
        }

        return Arrays.stream(this.redirectUris)
                .map(String::toString)
                .collect(Collectors.toSet());
    }


    public Set<String> getScopes() {
        if (checkCollection(this.scopes)) {
            return Collections.emptySet();
        }

        return Arrays.stream(this.scopes)
                .map(String::toString)
                .collect(Collectors.toSet());
    }


    // Метод проверки на то, чтобы коллекция существовала и была не пустой
    private boolean checkCollection(String[] data) {
        return data == null || data.length == 0;
    }


    public static OAuth2ClientEntityBuilder builder() {
        return new OAuth2ClientEntityBuilder();
    }


    public static final class OAuth2ClientEntityBuilder implements Builder<OAuth2ClientEntity> {

        private String _clientId;
        private String _clientSecret;
        private String _clientName;
        private LocalDateTime _clientSecretExpiresAt;
        private String[] _clientAuthenticationMethods;
        private String[] _authorizationGrantTypes;
        private String[] _redirectUris;
        private String[] _scopes;


        public OAuth2ClientEntityBuilder clientId(String clientId) {
            this._clientId = clientId;
            return this;
        }


        public OAuth2ClientEntityBuilder clientSecret(String clientSecret) {
            this._clientSecret = clientSecret;
            return this;
        }


        public OAuth2ClientEntityBuilder clientName(String clientName) {
            this._clientName = clientName;
            return this;
        }


        public OAuth2ClientEntityBuilder clientSecretExpiresAt(LocalDateTime clientSecretExpiresAt) {
            this._clientSecretExpiresAt = clientSecretExpiresAt;
            return this;
        }


        public OAuth2ClientEntityBuilder clientAuthenticationMethods(String[] clientAuthenticationMethods) {
            this._clientAuthenticationMethods = clientAuthenticationMethods;
            return this;
        }


        public OAuth2ClientEntityBuilder authorizationGrantTypes(String[] authorizationGrantTypes) {
            this._authorizationGrantTypes = authorizationGrantTypes;
            return this;
        }


        public OAuth2ClientEntityBuilder redirectUris(String[] redirectUris) {
            this._redirectUris = redirectUris;
            return this;
        }


        public OAuth2ClientEntityBuilder scopes(String[] scopes) {
            this._scopes = scopes;
            return this;
        }

        @Override
        public OAuth2ClientEntity build() {
            return new OAuth2ClientEntity(
                    this._clientId,
                    this._clientSecret,
                    this._clientName,
                    this._clientSecretExpiresAt,
                    this._clientAuthenticationMethods,
                    this._authorizationGrantTypes,
                    this._redirectUris,
                    this._scopes
            );
        }
    }
}
