package com.testing.auth_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.testing.auth_server.utils.StackElementJsonSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponseDto {

    @JsonProperty(namespace = "message")
    private String message;

    @JsonProperty(namespace = "stack_trace")
    @JsonSerialize(using = StackElementJsonSerializer.class)
    private StackTraceElement[] stackTrace;

}
