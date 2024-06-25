package com.testing.auth_server.service.common;


import com.testing.auth_server.dao.entity.UserEntity;
import com.testing.auth_server.dao.repository.UserRepository;
import com.testing.auth_server.dto.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.testing.auth_server.common.exception.UserNotFoundException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultUserServiceApi implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findById(Long id) throws Exception {
        return this.userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with id %s not found :(", id.toString()))
                );
    }

    @Override
    public UserEntity findByEmail(String email) throws Exception {
        return this.userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with email %s not found :(", email))
                );
    }

    @Override
    public UserEntity findByUsername(String username) throws Exception {
        return this.userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with username %s not found :(", username))
                );
    }



    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void registerUser(UserRegistrationDto dto) throws Exception {
        log.warn("Empty method!!!");
    }
}
