package com.example.test.app.controller;

import com.example.test.app.model.auth.LoginRequestDto;
import com.example.test.app.model.auth.LoginResponseDto;
import com.example.test.app.model.auth.RegisterDto;
import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody RegisterDto registerDto) {
        log.info("Запрос на регистрацию пользователя: {}", registerDto);
        UserResponseDto userResponseDto = authenticationService.registerUser(registerDto);
        log.info("Пользователь зарегистрирован: {}", userResponseDto);
        return userResponseDto;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("Запрос на авторизацию пользователя: {}", loginRequestDto.getUsername());
        LoginResponseDto loginResponseDto = authenticationService.loginUser(loginRequestDto);
        log.info("Авторизация выполнена: {}", loginResponseDto);
        return loginResponseDto;
    }

}
