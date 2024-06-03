package com.mocum.domain.user.domain;

import com.mocum.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.YearMonth;

@Entity
@Table(name = "MonthlySummary")
@NoArgsConstructor
@Getter
public class MonthlySummary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private float totalCalories;
    private float totalCarbohydrates;
    private float totalProtein;
    private float totalFat;

    private String month;
}