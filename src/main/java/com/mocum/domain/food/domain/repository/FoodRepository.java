package com.mocum.domain.food.domain.repository;

import com.mocum.domain.food.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT f FROM Food f WHERE f.foodName LIKE %:foodName%")
    List<Food> findAllByFoodName(String foodName);
}
