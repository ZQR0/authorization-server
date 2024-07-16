package com.testing.auth_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class UserRegistrationDto {

    @JsonProperty(namespace = "email", required = true)
    private String email;

    @JsonProperty(namespace = "username", required = true)
    private String username;

    @JsonProperty(namespace = "password", required = true)
    private String password;

    @JsonProperty(namespace = "birthday", required = true)
    private LocalDate birthday;

    @JsonProperty(namespace = "active", required = true)
    private boolean active;

}
