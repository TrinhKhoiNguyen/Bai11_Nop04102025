package com.example.jwt.dto;

import lombok.Data;

@Data
public class RegisterUserModel {
    private String email;
    private String password;
    private String fullName;
}
