package com.testing.auth_server.controller;

import lombok.Getter;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @GetMapping(path = "code")
    public String getTest(Authentication authentication) {
        return String.format("Hello, %s", authentication.getName());
    }


}
