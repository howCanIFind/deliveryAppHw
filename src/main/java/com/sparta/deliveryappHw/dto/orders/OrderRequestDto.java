package com.sparta.deliveryappHw.dto.orders;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {

    private Long restaurantId;

    private List<OrderRequestItem> foods;
}
