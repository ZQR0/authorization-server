package com.testing.auth_server.controller;


import com.testing.auth_server.dto.UserRegistrationDto;
import com.testing.auth_server.service.UserRegistrationService;
import com.testing.auth_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users/")
@RequiredArgsConstructor
public class UserController {

     private static final String MESSAGE = "User successfully registered";

    private final UserService userService;
    private final UserRegistrationService userRegistrationService;

    @GetMapping(path = "get-all/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsersEndpoint() {
        return new ResponseEntity<>(
                this.userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-id", params = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(
                this.userService.findById(id),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-email", params = "email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email") String email) throws Exception {
        return new ResponseEntity<>(
                this.userService.findByEmail(email),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-username", params = "email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUsername(@RequestParam(name = "username") String username) throws Exception {
        return new ResponseEntity<>(
                this.userService.findByUsername(username),
                HttpStatus.OK
        );
    }


    // TODO User registration endpoint
    @PostMapping(path = "sign-up", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto dto) {
        try {
            this.userRegistrationService.register(dto);
            return new ResponseEntity<>(
                    MESSAGE,
                    HttpStatus.OK
            );
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
