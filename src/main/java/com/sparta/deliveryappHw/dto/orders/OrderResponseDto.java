package com.sparta.deliveryappHw.dto.orders;

import com.sparta.deliveryappHw.model.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderResponseDto {
    private String restaurantName;

    private List<OrderResponseItem> foods;

    private int deliveryFee;

    private int totalPrice;



}
