package com.testing.auth_server.config.security.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
public class AuthorizedUserDetails implements UserDetails, OAuth2User {

    private Long id;
    private String email;
    private String username;
    private String password;
    private LocalDate birthday;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Map<String, Object> oauthAttributes;
    private Set<? extends GrantedAuthority> authorities; // Для authority используется Set, т.к. нужны только уникальные значения

    @Override
    @SuppressWarnings("unchecked")
    public <A> A getAttribute(String name) {
        return (A) this.oauthAttributes.get(name); // TODO attribute getting
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.oauthAttributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // ОЧЕНЬ ВАЖНО!!! Используем email вместо username, т.к. логин происходит через email
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    // ОЧЕНЬ ВАЖНО!!! Используем email вместо username, т.к. логин происходит через email
    @Override
    public String getName() {
        return this.email;
    }
}
