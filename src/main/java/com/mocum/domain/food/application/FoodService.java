package com.mocum.domain.food.application;

import com.mocum.domain.bookmark.domain.repository.BookmarkRepository;
import com.mocum.domain.food.domain.Food;
import com.mocum.domain.food.domain.repository.FoodRepository;
import com.mocum.domain.food.dto.FoodSearchRes;
import com.mocum.domain.food.dto.RegisterMealReq;
import com.mocum.domain.user.domain.DailyIntakes;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.DailyIntakeRepository;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final UserRepository userRepository;
    private final FoodRepository foodRepository;
    private final DailyIntakeRepository dailyIntakeRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public FoodSearchRes searchFood(String foodName) {
        List<Food> foods = foodRepository.findAllByFoodName(foodName);
        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);

        List<FoodSearchRes.FoodDto> foodDtos = foods.stream().map(food -> {
            FoodSearchRes.FoodDto dto = new FoodSearchRes.FoodDto();
            dto.setId(food.getId());
            dto.setFoodName(food.getFoodName());
            dto.setCalories(food.getCalories());
            dto.setCarbohydrates(food.getCarbohydrates());
            dto.setProtein(food.getProtein());
            dto.setFat(food.getFat());
            dto.setWater(food.getWater());

            // Check if the food is bookmarked by the user
            boolean isBookmarked = bookmarkRepository.findByUserAndFood(user, food).isPresent();
            dto.setBookmarked(isBookmarked);

            return dto;
        }).collect(Collectors.toList());

        FoodSearchRes response = new FoodSearchRes();
        response.setFoods(foodDtos);
        return response;

    }

    @Transactional
    public Message registerMeal(RegisterMealReq registerMealReq) {

        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);

        DailyIntakes dailyIntakes = DailyIntakes.builder()
                .user(user)
                .calories(registerMealReq.getCalories())
                .carbohydrates(registerMealReq.getCarbohydrates())
                .protein(registerMealReq.getProteins())
                .fat(registerMealReq.getFat())
                .mealType(registerMealReq.getMealType())
                .build();

        dailyIntakeRepository.save(dailyIntakes);


        return Message.builder()
                .message("식단이 등록되었습니다.")
                .build();
    }
}
