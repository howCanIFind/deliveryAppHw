package com.sparta.deliveryappHw.model;

import com.sparta.deliveryappHw.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Restaurant restaurant;

    public Food(FoodRequestDto requestDto, Restaurant restaurant) {
        this.foodName = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }

    public Food(String foodName, int price, Restaurant restaurant) {
        this.foodName = foodName;
        this.price = price;
        this.restaurant = restaurant;
    }
}
