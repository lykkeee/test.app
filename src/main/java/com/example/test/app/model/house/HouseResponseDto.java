package com.example.test.app.model.house;

import com.example.test.app.model.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDto {
    private Long id;
    private String address;
    private Long owner;
    private List<UserResponseDto> users;
}
