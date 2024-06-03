package com.mocum.domain.user.domain;

import com.mocum.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DailyIntake")
@NoArgsConstructor
@Getter
public class DailyIntakes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private float calories;
    private float carbohydrates;
    private float protein;
    private float fat;
    private String mealType;


    @Builder
    public DailyIntakes(User user, float calories, float carbohydrates, float protein, float fat, String mealType) {
        this.user = user;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.mealType = mealType;
    }
}
