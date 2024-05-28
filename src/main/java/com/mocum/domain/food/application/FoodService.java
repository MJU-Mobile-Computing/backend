package com.mocum.domain.food.application;

import com.mocum.domain.food.domain.Food;
import com.mocum.domain.food.domain.repository.FoodRepository;
import com.mocum.domain.food.dto.FoodSearchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public FoodSearchRes searchFood(String foodName) {
        List<Food> foods = foodRepository.findAllByFoodName(foodName);

        List<FoodSearchRes.FoodDto> foodDtos = foods.stream().map(food -> {
            FoodSearchRes.FoodDto dto = new FoodSearchRes.FoodDto();
            dto.setId(food.getId());
            dto.setFoodName(food.getFoodName());
            dto.setCalories(food.getCalories());
            dto.setCarbohydrates(food.getCarbohydrates());
            dto.setProtein(food.getProtein());
            dto.setFat(food.getFat());
            dto.setWater(food.getWater());
            return dto;
        }).collect(Collectors.toList());

        FoodSearchRes response = new FoodSearchRes();
        response.setFoods(foodDtos);
        return response;
    }
}
