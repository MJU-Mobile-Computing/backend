package com.mocum.domain.user.application;

import com.mocum.domain.exercise.domain.Exercise;
import com.mocum.domain.exercise.domain.repository.ExerciseRepository;
import com.mocum.domain.user.domain.DailyIntakes;
import com.mocum.domain.user.domain.repository.DailyIntakeRepository;
import com.mocum.domain.user.dto.MainRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final DailyIntakeRepository dailyIntakeRepository;
    private final ExerciseRepository exerciseRepository;

    @Transactional
    public MainRes viewMainPage(Long userId) {
        YearMonth currentMonth = YearMonth.now();
        LocalDate startLocalDate = currentMonth.atDay(1);
        LocalDate endLocalDate = currentMonth.atEndOfMonth();

        // 총 운동 시간
        Long totalExerciseHours = exerciseRepository.findTotalExerciseHoursByUserId(userId).orElseThrow(NullPointerException::new);


        LocalDateTime startDate = startLocalDate.atStartOfDay();
        LocalDateTime endDate = endLocalDate.atTime(23, 59, 59);

        List<Object[]> monthlyIntakeSummary = dailyIntakeRepository.findMonthlyIntakeByUserId(userId, startDate, endDate);

        float totalCalories = 0;
        float totalCarbohydrates = 0;
        float totalProteins = 0;
        float totalFat = 0;

        for (Object[] result : monthlyIntakeSummary) {
            totalCalories += ((Number) result[0]).floatValue();
            totalCarbohydrates += ((Number) result[1]).floatValue();
            totalProteins += ((Number) result[2]).floatValue();
            totalFat += ((Number) result[3]).floatValue();
        }

        return MainRes.builder()
                .totalCalories(totalCalories)
                .totalCarbohydrate(totalCarbohydrates)
                .totalProteins(totalProteins)
                .totalFat(totalFat)
                .totalExerciseTime(totalExerciseHours)
                .build();
    }

    @Transactional
    public MainRes viewMainPageByDate(Long userId, LocalDate date) {
        return getMainPageData(userId, date);
    }

    private MainRes getMainPageData(Long userId, LocalDate date) {
        // 총 운동 시간
        Long totalExerciseHours = exerciseRepository.findTotalExerciseHoursByUserId(userId).orElse(0L);

        LocalDateTime startDate = date.atStartOfDay();
        LocalDateTime endDate = date.atTime(23, 59, 59);

        List<Object[]> dailyIntakeSummary = dailyIntakeRepository.findDailyIntakeByUserId(userId, startDate, endDate);

        float totalCalories = 0;
        float totalCarbohydrates = 0;
        float totalProteins = 0;
        float totalFat = 0;

        for (Object[] result : dailyIntakeSummary) {
            totalCalories += result[0] != null ? ((Number) result[0]).floatValue() : 0;
            totalCarbohydrates += result[1] != null ? ((Number) result[1]).floatValue() : 0;
            totalProteins += result[2] != null ? ((Number) result[2]).floatValue() : 0;
            totalFat += result[3] != null ? ((Number) result[3]).floatValue() : 0;
        }

        return MainRes.builder()
                .totalCalories(totalCalories)
                .totalCarbohydrate(totalCarbohydrates)
                .totalProteins(totalProteins)
                .totalFat(totalFat)
                .totalExerciseTime(totalExerciseHours)
                .build();
    }

}
