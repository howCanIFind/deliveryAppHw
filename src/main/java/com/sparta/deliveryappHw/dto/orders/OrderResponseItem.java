package com.sparta.deliveryappHw.dto.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderResponseItem {

    private String name;

    private int quantity;

    private int price;

    public OrderResponseItem(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
