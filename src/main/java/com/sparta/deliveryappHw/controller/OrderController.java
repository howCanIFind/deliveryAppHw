package com.sparta.deliveryappHw.controller;

import com.sparta.deliveryappHw.dto.orders.OrderRequestDto;
import com.sparta.deliveryappHw.dto.orders.OrderRequestItem;
import com.sparta.deliveryappHw.dto.orders.OrderResponseDto;
import com.sparta.deliveryappHw.model.Orders;
import com.sparta.deliveryappHw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public OrderResponseDto orderRequest(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.requestOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getAllorder() {

        return orderService.findAllOrders();
    }
}
