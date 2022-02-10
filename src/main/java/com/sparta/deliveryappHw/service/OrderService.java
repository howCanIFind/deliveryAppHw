package com.sparta.deliveryappHw.service;

import com.sparta.deliveryappHw.dto.orders.OrderRequestDto;
import com.sparta.deliveryappHw.dto.orders.OrderRequestItem;
import com.sparta.deliveryappHw.dto.orders.OrderResponseDto;
import com.sparta.deliveryappHw.dto.orders.OrderResponseItem;
import com.sparta.deliveryappHw.model.Food;
import com.sparta.deliveryappHw.model.OrderDetails;
import com.sparta.deliveryappHw.model.Orders;
import com.sparta.deliveryappHw.model.Restaurant;
import com.sparta.deliveryappHw.repository.FoodRepository;
import com.sparta.deliveryappHw.repository.OrderDetailsRepository;
import com.sparta.deliveryappHw.repository.OrderRepository;
import com.sparta.deliveryappHw.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            FoodRepository foodRepository,
            RestaurantRepository restaurantRepository,
            OrderDetailsRepository orderDetailsRepository
    ) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }
//    Long restaurantId, List<OrderRequestItem> orderList
    public OrderResponseDto requestOrder(OrderRequestDto orderRequestDto) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        System.out.println(restaurantId);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        List<OrderRequestItem> orderRequestItemList = orderRequestDto.getFoods();
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<OrderResponseItem> orderResponseItemList = new ArrayList<>();
        int totalPrice = 0;
        for(OrderRequestItem orderItem : orderRequestItemList) {
            Food food = foodRepository.findById(orderItem.getId()).orElseThrow(
                    () -> new NullPointerException("해당 food가 존재하지 않습니다.")
            );
            System.out.println(food.getFoodName());
            //음식 수량이 맞지 않으면 에러발생.
            if(orderItem.getQuantity() <1 || orderItem.getQuantity() > 100) {
                throw new IllegalStateException("올바른 수량이 아닙니다.");
            }

            int foodPrice = food.getPrice()*orderItem.getQuantity();
            OrderDetails orderDetails = new OrderDetails(
                    food.getFoodName(),
                    orderItem.getQuantity(),
                    foodPrice,
                    food
                    );
            OrderResponseItem orderResponseItem = new OrderResponseItem(
                    food.getFoodName(),
                    orderItem.getQuantity(),
                    foodPrice
            );
            orderDetailsRepository.save(orderDetails);
            orderDetailsList.add(orderDetails);
            orderResponseItemList.add(orderResponseItem);
            totalPrice += foodPrice;
        }

        System.out.println("----------");
        for(OrderDetails orderDetail : orderDetailsList) {
            System.out.println(orderDetail.getName());
        }
        if(totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalStateException("최소 주문 가격에 충족하지 못했습니다.");
        }
        totalPrice += restaurant.getDeliveryFee();

        Orders orders = new Orders(
                restaurant.getName(),
                totalPrice,
                restaurant.getDeliveryFee(),
                orderDetailsList
        );

        System.out.println(orderDetailsList.size());
        orderRepository.save(orders);
        OrderResponseDto orderResponseDto = new OrderResponseDto(restaurant.getName(),orderResponseItemList, restaurant.getDeliveryFee(), totalPrice);
        return orderResponseDto;
    }


    public List<OrderResponseDto> findAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for(Orders orders : ordersList) {
            List<OrderResponseItem> orderResponseItemList = new ArrayList<>();
            for(OrderDetails orderDetails : orders.getDetailsList()) {
                OrderResponseItem orderResponseItem = new OrderResponseItem(
                        orderDetails.getName(),
                        orderDetails.getQuantity(),
                        orderDetails.getPrice()
                        );
                orderResponseItemList.add(orderResponseItem);
            }
            OrderResponseDto orderResponseDto = new OrderResponseDto(
                    orders.getRestaurantName(),
                    orderResponseItemList,
                    orders.getDeliveryFee(),
                    orders.getTotalPrice());
            orderResponseDtos.add(orderResponseDto);
            System.out.println(orders.getDeliveryFee());
        }

        return orderResponseDtos;
    }
}
