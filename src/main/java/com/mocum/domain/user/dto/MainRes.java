package com.mocum.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainRes {
    private float totalCalories;
    private float totalCarbohydrate;
    private float totalProteins;
    private float totalFat;
    private Long totalExerciseTime;
    private float goalCalories;
}
