package com.testing.auth_server.controller;


import com.testing.auth_server.common.exception.AuthorityAlreadyExistsException;
import com.testing.auth_server.common.exception.AuthorityNotFoundException;
import com.testing.auth_server.common.exception.UserAlreadyExistsException;
import com.testing.auth_server.common.exception.UserNotFoundException;
import com.testing.auth_server.dto.ErrorResponseDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.hibernate.query.SyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Users handlers

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(@NonNull UserNotFoundException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsExceptionHandler(@NonNull UserAlreadyExistsException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    // Authorities handlers

    @ExceptionHandler(AuthorityNotFoundException.class)
    public ResponseEntity<?> authorityNotFoundExceptionHandler(@NonNull AuthorityNotFoundException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AuthorityAlreadyExistsException.class)
    public ResponseEntity<?> authorityAlreadyExistsExceptionHandler(@NonNull AuthorityAlreadyExistsException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    // Other

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<?> propertyValueExceptionHandler(@NonNull PropertyValueException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }


    // Hibernate

    @ExceptionHandler(SyntaxException.class)
    public ResponseEntity<?> hibernateSyntaxException(@NonNull SyntaxException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> sqlExceptionHandler(@NonNull SQLException exception) {
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .message(exception.getMessage())
                        .stackTrace(exception.getStackTrace())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
