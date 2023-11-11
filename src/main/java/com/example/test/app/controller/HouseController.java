package com.example.test.app.controller;

import com.example.test.app.model.house.HouseRequestDto;
import com.example.test.app.model.house.HouseResponseDto;
import com.example.test.app.model.house.HouseUpdateDto;
import com.example.test.app.service.house.HouseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/houses")
@Slf4j
@AllArgsConstructor
public class HouseController {

    private final HouseServiceImpl houseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HouseResponseDto createHouse(@RequestBody @Valid HouseRequestDto houseRequestDto) {
        log.info("Запрос на создание дома: {}", houseRequestDto);
        HouseResponseDto house = houseService.createHouse(houseRequestDto);
        log.info("Дом создан: {}", house);
        return house;
    }

    @PatchMapping("/{houseId}")
    public HouseResponseDto updateHouse(@RequestBody HouseUpdateDto houseUpdateDto,
                                        @PathVariable Long houseId) {
        log.info("Запрос на обновление дома: {}", houseUpdateDto);
        HouseResponseDto house = houseService.updateHouse(houseUpdateDto, houseId);
        log.info("Дом обновлен: {}", house);
        return house;
    }

    @PatchMapping("/{houseId}/{ownerId}/{userId}")
    public HouseResponseDto addUserToHouse(@PathVariable Long houseId,
                                           @PathVariable Long ownerId,
                                           @PathVariable Long userId) {
        log.info("Запрос на добавление пользователя id={} в дом id={}", userId,  houseId);
        HouseResponseDto house = houseService.addUserToHouse(houseId, ownerId, userId);
        log.info("Пользователь id={} добавлен в дом id={}: {}", userId, houseId, house);
        return house;
    }

    @GetMapping("/{houseId}")
    public HouseResponseDto getHouse(@PathVariable Long houseId) {
        log.info("Запрос на получение дома с id: {}", houseId);
        HouseResponseDto house = houseService.getHouse(houseId);
        log.info("Дом получен: {}", house);
        return house;
    }

    @GetMapping
    public List<HouseResponseDto> getAllHouses() {
        log.info("Запрос на получение всех домов");
        List<HouseResponseDto> houses = houseService.getAllHouses();
        log.info("Список домов получен: {}", houses);
        return houses;
    }

    @PatchMapping("/{houseId}")
    public void deleteHouse(@PathVariable Long houseId) {
        log.info("Запрос на удаление дома с id: {}", houseId);
        houseService.deleteHouse(houseId);
        log.info("Дом удален");
    }

}