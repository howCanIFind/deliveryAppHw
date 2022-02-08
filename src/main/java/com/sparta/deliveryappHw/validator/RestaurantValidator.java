package com.sparta.deliveryappHw.validator;

import com.sparta.deliveryappHw.dto.RestaurantDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public static void validatorRestaurantInput(RestaurantDto restaurantDto) {

        if(restaurantDto.getMinOrderPrice()<1_000 || restaurantDto.getMinOrderPrice()>10_0000) {
            throw new IllegalStateException();
        }
        if(restaurantDto.getMinOrderPrice()%100 != 0) {
            throw new IllegalStateException();
        }
        if(restaurantDto.getDeliveryFee()<0 || restaurantDto.getDeliveryFee()>10_000) {
            throw new IllegalStateException();
        }
        if(restaurantDto.getDeliveryFee()%500 != 0) {
            throw new IllegalStateException();
        }
    }

}
