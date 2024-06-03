package com.mocum.domain.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMealReq {
    private float calories;
    private float carbohydrates;
    private float proteins;
    private float fat;
    private String mealType;
}
