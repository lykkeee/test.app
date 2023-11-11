package com.example.test.app.model.auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String password;
    private Integer age;
}
