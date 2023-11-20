package com.example.test.app.model.auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private Integer age;
}
