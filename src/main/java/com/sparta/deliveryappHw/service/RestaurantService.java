package com.sparta.deliveryappHw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.deliveryappHw.dto.RestaurantDto;
import com.sparta.deliveryappHw.model.Restaurant;
import com.sparta.deliveryappHw.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant registerRestaurant(RestaurantDto requestDto) {
        Restaurant restaurant = new Restaurant(requestDto);

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }
}
