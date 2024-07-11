package com.krishnavamshi.konnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishnavamshi.konnect.config.JwtProvider;
import com.krishnavamshi.konnect.models.User;
import com.krishnavamshi.konnect.request.LoginRequest;
import com.krishnavamshi.konnect.response.AuthResponse;
import com.krishnavamshi.konnect.services.CustomUserDetailsService;
import com.krishnavamshi.konnect.services.UserService;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody User user) throws Exception {
        AuthResponse newUser = userService.registerUser(user);
        return newUser;
        // return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse logIn(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        User user = userService.findUserByEmail(loginRequest.getEmail());
        return new AuthResponse(token, "Logged In Successfully", user);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails==null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
