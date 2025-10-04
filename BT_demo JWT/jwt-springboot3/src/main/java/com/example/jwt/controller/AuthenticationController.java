package com.example.jwt.controller;

import com.example.jwt.dto.LoginResponse;
import com.example.jwt.dto.LoginUserModel;
import com.example.jwt.dto.RegisterUserModel;
import com.example.jwt.model.User;
import com.example.jwt.security.JwtUtils;
import com.example.jwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtUtils jwtUtils;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtUtils jwtUtils, AuthenticationService authenticationService) {
        this.jwtUtils = jwtUtils;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<User> register(@RequestBody RegisterUserModel registerUser) {
        User registeredUser = authenticationService.signup(registerUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(path = "/login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);

        String jwtToken = jwtUtils.generateToken(org.springframework.security.core.userdetails.User
                .withUsername(authenticatedUser.getEmail())
                .password(authenticatedUser.getPassword())
                .authorities("USER")
                .build());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtUtils.getExpirationMs());

        return ResponseEntity.ok(loginResponse);
    }
}
