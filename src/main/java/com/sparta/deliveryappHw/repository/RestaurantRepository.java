package com.sparta.deliveryappHw.repository;

import com.sparta.deliveryappHw.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
