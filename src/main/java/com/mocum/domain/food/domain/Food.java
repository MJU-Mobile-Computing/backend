package com.mocum.domain.food.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "calories")
    private double calories;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "water")
    private double water;


    @Builder
    public Food(String foodName, double calories, double carbohydrates, double protein, double fat, double water) {
        this.foodName = foodName;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.water = water;
    }
}
