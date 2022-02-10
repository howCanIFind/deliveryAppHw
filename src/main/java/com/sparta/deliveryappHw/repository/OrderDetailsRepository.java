package com.sparta.deliveryappHw.repository;

import com.sparta.deliveryappHw.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
