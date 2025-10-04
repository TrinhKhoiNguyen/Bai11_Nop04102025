package com.example.jwt.dto;

import lombok.Data;

@Data
public class LoginUserModel {
    private String email;
    private String password;
}
