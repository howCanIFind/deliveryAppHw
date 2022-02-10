package com.sparta.deliveryappHw.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @OneToMany
    @JoinColumn(name = "ORDERDETAILS_ID")
    private List<OrderDetails> detailsList;

    public Orders(
            String restaurantName,
            int totalPrice,
            int deliveryFee,
            List<OrderDetails> detailsList
    ) {
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.detailsList = detailsList;

    }
}
