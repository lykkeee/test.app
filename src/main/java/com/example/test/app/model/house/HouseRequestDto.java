package com.example.test.app.model.house;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDto {
    @NotBlank
    private String address;
    @NotNull
    private Long owner;
}
