package com.sparta.deliveryappHw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    private Food food;

    @ManyToOne
    private Orders orders;

    public OrderDetails(String name, int quantity, int price, Food food) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.food = food;
    }
}
