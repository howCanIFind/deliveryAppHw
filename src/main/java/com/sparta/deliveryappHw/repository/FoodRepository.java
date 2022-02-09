package com.sparta.deliveryappHw.repository;

import com.sparta.deliveryappHw.model.Food;
import com.sparta.deliveryappHw.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
}
