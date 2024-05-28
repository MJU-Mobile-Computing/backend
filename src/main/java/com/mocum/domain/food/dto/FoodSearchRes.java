package com.mocum.domain.food.dto;

import lombok.Data;

import java.util.List;

@Data
public class FoodSearchRes {
    private List<FoodDto> foods;

    @Data
    public static class FoodDto {
        private Long id;
        private String foodName;
        private double calories;
        private double carbohydrates;
        private double protein;
        private double fat;
        private double water;
    }
}
