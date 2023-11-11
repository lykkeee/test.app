package com.example.test.app.service.house;

import com.example.test.app.model.house.HouseRequestDto;
import com.example.test.app.model.house.HouseResponseDto;
import com.example.test.app.model.house.HouseUpdateDto;

import java.util.List;

public interface HouseService {

    HouseResponseDto createHouse(HouseRequestDto houseRequestDto);

    HouseResponseDto updateHouse(HouseUpdateDto houseUpdateDto, Long houseId);

    HouseResponseDto addUserToHouse(Long houseId, Long ownerId, Long userId);

    HouseResponseDto getHouse(Long houseId);

    List<HouseResponseDto> getAllHouses();

    void deleteHouse(Long houseId);

}
