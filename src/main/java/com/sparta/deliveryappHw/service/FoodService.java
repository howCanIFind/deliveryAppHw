package com.sparta.deliveryappHw.service;

import com.sparta.deliveryappHw.dto.FoodRequestDto;
import com.sparta.deliveryappHw.dto.FoodResponseDto;
import com.sparta.deliveryappHw.model.Food;
import com.sparta.deliveryappHw.model.Restaurant;
import com.sparta.deliveryappHw.repository.FoodRepository;
import com.sparta.deliveryappHw.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //음식 등록.
    public void registerFood(List<FoodRequestDto> requestDtos, Long restaurantId) throws IllegalStateException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        //입려된 음식명에 중복 에러.
        List<String> tempStr = new ArrayList<>();
        for(FoodRequestDto requestDto : requestDtos) {
            tempStr.add(requestDto.getName());
        }
        if(tempStr.size() != tempStr.stream().distinct().count()) {
            throw new IllegalStateException("중복 에러");
        }

        List<Food> foodList = foodRepository.findAllByRestaurant(restaurant);
        List<String> foodNames = new ArrayList<>();
        for(Food food : foodList) {
            foodNames.add(food.getFoodName());
        }

        for(FoodRequestDto requestDto : requestDtos) {
            Food food = new Food(requestDto.getName(), requestDto.getPrice(), restaurant);
            //Validation.

            if(foodNames.contains(requestDto.getName())) {
                throw new IllegalStateException("중복된 음식 이름.");
            }

            if(food.getPrice()<100 || food.getPrice()>1000000 || food.getPrice()%100!=0) {
                throw new IllegalStateException("허용되지 않는 값.");
            }
            foodRepository.save(food);
        }

    }

    //음식점의 모든 음식 조회
    @Transactional
    public List<FoodResponseDto> getFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        List<Food> foods = foodRepository.findAllByRestaurant(restaurant);
        List<FoodResponseDto> foodsResponse = new ArrayList<>();
        Long id = 1L;
        for(Food food : foods) {
            FoodResponseDto temp = new FoodResponseDto(id++,food.getFoodName(), food.getPrice());
            foodsResponse.add(temp);
        }

        return foodsResponse;
    }
}
