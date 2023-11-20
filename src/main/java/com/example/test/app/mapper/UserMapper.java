package com.example.test.app.mapper;

import com.example.test.app.model.user.ApplicationUser;
import com.example.test.app.model.user.UserUpdateDto;

public class UserMapper {

    public static ApplicationUser toModelUpdate(UserUpdateDto userUpdateDto, ApplicationUser applicationUser) {
        if (userUpdateDto.getUsername() != null) {
            applicationUser.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getAge() != null) {
            applicationUser.setAge(userUpdateDto.getAge());
        }
        if (userUpdateDto.getPassword() != null) {
            applicationUser.setPassword(userUpdateDto.getPassword());
        }
        return applicationUser;
    }

}
