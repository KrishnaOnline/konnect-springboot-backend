package com.krishnavamshi.konnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.response.AuthResponse;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        AuthResponse newUser = userService.registerUser(user);
        return newUser;
        // return userService.registerUser(user);
    }
}
