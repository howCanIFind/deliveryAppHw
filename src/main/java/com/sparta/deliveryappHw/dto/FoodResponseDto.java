package com.sparta.deliveryappHw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;
}
