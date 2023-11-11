package com.example.test.app.controller;

import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.model.user.UserUpdateDto;
import com.example.test.app.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PatchMapping("/{userId}")  //Также значение можно передать через параметры
    public UserResponseDto updateUser(@RequestBody UserUpdateDto userUpdateDto,
                                      @PathVariable Long userId) {
        log.info("Запрос на обновление пользователя c id: {}", userId);
        UserResponseDto user = userService.updateUser(userUpdateDto, userId);
        log.info("Пользователь обновлен: {}", user);
        return user;
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        log.info("Запрос на получение пользователя с id: {}", userId);
        UserResponseDto user = userService.getUser(userId);
        log.info("Пользователь получен: {}", user);
        return user;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        log.info("Запрос на получение всех пользователей");
        List<UserResponseDto> users = userService.getAllUsers();
        log.info("Список пользователей получен: {}", users);
        return users;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        log.info("Запрос на удаление пользователя с id: {}", userId);
        userService.deleteUser(userId);
        log.info("Пользователь удален");
    }

}
