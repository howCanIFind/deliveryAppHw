package com.sparta.deliveryappHw.controller;

import com.sparta.deliveryappHw.dto.FoodRequestDto;
import com.sparta.deliveryappHw.dto.FoodResponseDto;
import com.sparta.deliveryappHw.model.Food;
import com.sparta.deliveryappHw.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public Void foodRegister(@RequestBody List<FoodRequestDto> requestDto,
                                   @PathVariable Long restaurantId
    ) {
        foodService.registerFood(requestDto, restaurantId);
        return null;
    }

    @GetMapping("restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFood(@PathVariable Long restaurantId) {
        return foodService.getFoods(restaurantId);
    }
}
