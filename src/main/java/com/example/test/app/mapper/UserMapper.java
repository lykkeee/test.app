package com.example.test.app.mapper;

import com.example.test.app.model.user.User;
import com.example.test.app.model.user.UserUpdateDto;

public class UserMapper {

    public static User toModelUpdate(UserUpdateDto userUpdateDto, User user) {
        if (userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if (userUpdateDto.getAge() != null) {
            user.setAge(userUpdateDto.getAge());
        }
        if (userUpdateDto.getPassword() != null) {
            user.setPassword(userUpdateDto.getPassword());
        }
        return user;
    }

}
