package com.example.test.app.model.auth;

import com.example.test.app.model.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private UserResponseDto user;
    private String jwt;
}
