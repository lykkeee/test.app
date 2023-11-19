package com.example.test.app.service.house;

import com.example.test.app.exception.ConflictException;
import com.example.test.app.exception.DataNotFoundException;
import com.example.test.app.mapper.HouseMapper;
import com.example.test.app.model.house.House;
import com.example.test.app.model.house.HouseRequestDto;
import com.example.test.app.model.house.HouseResponseDto;
import com.example.test.app.model.house.HouseUpdateDto;
import com.example.test.app.model.user.ApplicationUser;
import com.example.test.app.repository.HouseRepository;
import com.example.test.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;

    @Override
    public HouseResponseDto createHouse(HouseRequestDto houseRequestDto) {
        House house = HouseMapper.toModel(getUserExists(houseRequestDto.getOwner()), houseRequestDto);
        return HouseMapper.toResponse(houseRepository.save(house));
    }

    @Override
    public HouseResponseDto updateHouse(HouseUpdateDto houseUpdateDto, Long houseId) {
        ApplicationUser applicationUser = null;
        if (houseUpdateDto.getOwner() != null) {
            applicationUser = getUserExists(houseUpdateDto.getOwner());  //Если хотим изменить владельца, то проверяем существует ли такой пользователь
        }
        House house = HouseMapper.toModel(houseUpdateDto, getHouseExists(houseId), applicationUser);
        return HouseMapper.toResponse(houseRepository.save(house));
    }

    @Override
    public HouseResponseDto addUserToHouse(Long houseId, Long ownerId, Long userId) {
        ApplicationUser applicationUser = getUserExists(userId);
        getUserExists(ownerId);
        House house = getHouseExists(houseId); //Здесь опустил проверку на то, чтобы нельзя было добавить пользователя, если он является владельцем
        if (!house.getOwner().getId().equals(ownerId)) {  //проверяем владельца
            throw  new ConflictException("User with id=" + ownerId + " is not houses owner");
        }
        house.getApplicationUsers().add(applicationUser);
        return HouseMapper.toResponse(houseRepository.save(house));
    }

    @Override
    public HouseResponseDto getHouse(Long houseId) {
        return HouseMapper.toResponse(getHouseExists(houseId));
    }

    @Override
    public List<HouseResponseDto> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream().map(HouseMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteHouse(Long houseId) {
        getHouseExists(houseId);
        houseRepository.deleteById(houseId);
    }

    private ApplicationUser getUserExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with id=" + userId + " was not found"));
    }

    private House getHouseExists(Long houseId) {
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new DataNotFoundException("House with id=" + houseId + " was not found"));
    }
}
