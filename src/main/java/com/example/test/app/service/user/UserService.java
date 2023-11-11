package com.example.test.app.service.user;

import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.model.user.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId);

    UserResponseDto getUser(Long userId);

    List<UserResponseDto> getAllUsers();

    void deleteUser(Long userId);

}
