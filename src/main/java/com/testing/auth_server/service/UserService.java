package com.testing.auth_server.service;

import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity findById(Long id) throws Exception;
    UserEntity findByEmail(String email) throws Exception;
    UserEntity findByUsername(String username) throws Exception;
}
