package com.sparta.deliveryappHw.model;

import com.sparta.deliveryappHw.dto.RestaurantDto;
import com.sparta.deliveryappHw.validator.RestaurantValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 레스토랑 이름.
    @Column(nullable = false)
    private String name;
    // 최소주문 가격.
    @Column(nullable = false)
    private int minOrderPrice;
    // 기본 배달비.
    @Column(nullable = false)
    private int deliveryFee;



    public Restaurant(RestaurantDto requestDto) {

        //입력값 Validation
        RestaurantValidator.validatorRestaurantInput(requestDto);

        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}