package com.example.test.app.mapper;

import com.example.test.app.model.house.House;
import com.example.test.app.model.house.HouseRequestDto;
import com.example.test.app.model.house.HouseResponseDto;
import com.example.test.app.model.house.HouseUpdateDto;
import com.example.test.app.model.user.ApplicationUser;
import com.example.test.app.model.user.UserResponseDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HouseMapper {

    public static House toModel(ApplicationUser applicationUser, HouseRequestDto houseRequestDto) {
        House house = new House();
        house.setAddress(houseRequestDto.getAddress());
        house.setOwner(applicationUser);
        house.setApplicationUsers(new HashSet<>());
        return house;
    }

    public static House toModel(HouseUpdateDto houseUpdateDto, House house, ApplicationUser applicationUser) {
        if (houseUpdateDto.getAddress() != null) {
            house.setAddress(houseUpdateDto.getAddress());
        }
        if (applicationUser != null) {
            house.setOwner(applicationUser);
        }
        return house;
    }

    public static HouseResponseDto toResponse(House house) {
        ModelMapper mapper = new ModelMapper();
        HouseResponseDto houseResponseDto = new HouseResponseDto();
        houseResponseDto.setId(house.getId());
        List<UserResponseDto> list = new ArrayList<>();
        for (ApplicationUser applicationUser : house.getApplicationUsers()) {
            UserResponseDto map = mapper.map(applicationUser, UserResponseDto.class);
            list.add(map);
        }
        houseResponseDto.setUsers(list);
        houseResponseDto.setAddress(house.getAddress());
        houseResponseDto.setOwner(house.getOwner().getId());
        return houseResponseDto;
    }

}
