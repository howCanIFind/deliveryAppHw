package com.sparta.deliveryappHw.controller;

import com.sparta.deliveryappHw.dto.RestaurantDto;
import com.sparta.deliveryappHw.model.Restaurant;
import com.sparta.deliveryappHw.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public Restaurant RegisterRestaurant (@RequestBody RestaurantDto requestDto) {
        return restaurantService.registerRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurnats(RestaurantDto requestDto) {
        return restaurantService.getAllRestaurants();
    }
}
