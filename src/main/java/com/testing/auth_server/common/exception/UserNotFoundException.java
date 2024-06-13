package com.testing.auth_server.common.exception;

public class UserNotFoundException extends Throwable {
    UserNotFoundException(String message) {
        super(message);
    }
}
