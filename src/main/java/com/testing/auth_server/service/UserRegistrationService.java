package com.testing.auth_server.service;

import com.testing.auth_server.dto.UserRegistrationDto;


/**
 * Базовый интерфейс для реализации логики регистрации пользователя в системе
 * сервера авторизации
 * */
public interface UserRegistrationService {
    void register(UserRegistrationDto dto) throws Exception;
}
