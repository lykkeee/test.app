package com.example.test.app.model.auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
