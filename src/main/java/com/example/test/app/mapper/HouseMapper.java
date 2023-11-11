package com.example.test.app.mapper;

import com.example.test.app.model.house.House;
import com.example.test.app.model.house.HouseRequestDto;
import com.example.test.app.model.house.HouseResponseDto;
import com.example.test.app.model.house.HouseUpdateDto;
import com.example.test.app.model.user.User;
import com.example.test.app.model.user.UserResponseDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HouseMapper {

    public static House toModel(User user, HouseRequestDto houseRequestDto) {
        House house = new House();
        house.setAddress(houseRequestDto.getAddress());
        house.setOwner(user);
        house.setUsers(new HashSet<>());
        return house;
    }

    public static House toModel(HouseUpdateDto houseUpdateDto, House house, User user) {
        if (houseUpdateDto.getAddress() != null) {
            house.setAddress(houseUpdateDto.getAddress());
        }
        if (user != null) {
            house.setOwner(user);
        }
        return house;
    }

    public static HouseResponseDto toResponse(House house) {
        ModelMapper mapper = new ModelMapper();
        HouseResponseDto houseResponseDto = new HouseResponseDto();
        houseResponseDto.setId(house.getId());
        List<UserResponseDto> list = new ArrayList<>();
        for (User user : house.getUsers()) {
            UserResponseDto map = mapper.map(user, UserResponseDto.class);
            list.add(map);
        }
        houseResponseDto.setUsers(list);
        houseResponseDto.setAddress(house.getAddress());
        houseResponseDto.setOwner(house.getOwner().getId());
        return houseResponseDto;
    }

}
