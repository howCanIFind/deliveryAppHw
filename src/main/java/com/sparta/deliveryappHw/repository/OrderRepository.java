package com.sparta.deliveryappHw.repository;

import com.sparta.deliveryappHw.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Orders, Long> {
}
