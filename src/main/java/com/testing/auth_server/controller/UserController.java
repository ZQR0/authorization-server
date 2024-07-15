package com.testing.auth_server.controller;


import com.testing.auth_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "get-all/")
    public ResponseEntity<?> getAllUsersEndpoint() {
        return new ResponseEntity<>(
                this.userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-id", params = "id")
    public ResponseEntity<?> getUserById(@RequestParam(name = "id") Long id) throws Exception {
        return new ResponseEntity<>(
                this.userService.findById(id),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-email", params = "email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email") String email) throws Exception {
        return new ResponseEntity<>(
                this.userService.findByEmail(email),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "by-username", params = "email")
    public ResponseEntity<?> getUserByUsername(@RequestParam(name = "username") String username) throws Exception {
        return new ResponseEntity<>(
                this.userService.findByUsername(username),
                HttpStatus.OK
        );
    }

    // TODO User registration endpoint

}
