package com.sparta.deliveryappHw.dto;

import lombok.*;


@Getter
@Setter
@Builder
public class RestaurantDto {
    // 레스토랑 이름.
    private String name;
    // 최소주문 가격.
    private int minOrderPrice;
    // 기본 배달비.
    private int deliveryFee;
}
