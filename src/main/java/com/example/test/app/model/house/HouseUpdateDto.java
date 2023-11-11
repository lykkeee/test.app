package com.example.test.app.model.house;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseUpdateDto {
    private String address;
    private Long owner;
}
