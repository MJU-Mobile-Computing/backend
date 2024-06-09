package com.mocum.domain.user.application;

import com.mocum.domain.exercise.domain.repository.ExerciseRepository;
import com.mocum.domain.user.domain.MonthlySummary;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.DailyIntakeRepository;
import com.mocum.domain.user.domain.repository.MonthlySummaryRepository;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.domain.user.dto.GoalCalReq;
import com.mocum.domain.user.dto.MainRes;
import com.mocum.global.payload.Message;
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
    private final MonthlySummaryRepository monthlySummaryRepository;
    private final UserRepository userRepository;

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

        Long totalExerciseHours = exerciseRepository.findDailyExerciseHoursByUserId(userId, startDate, endDate).orElse(0L);

        MonthlySummary monthlySummary = monthlySummaryRepository.findTopByUserOrderByCreatedAtDesc(userRepository.findById(userId).orElseThrow(NullPointerException::new))
                .orElse(new MonthlySummary(0, userRepository.findById(userId).orElseThrow(NullPointerException::new), YearMonth.now().toString()));

        return MainRes.builder()
                .totalCalories(totalCalories)
                .totalCarbohydrate(totalCarbohydrates)
                .totalProteins(totalProteins)
                .totalFat(totalFat)
                .totalExerciseTime(totalExerciseHours)
                .goalCalories(monthlySummary.getGoalCalories())
                .build();
    }


    @Transactional
    public Message setGoalCalories(long l, GoalCalReq goalCalReq) {
        User user = userRepository.findById(l).orElseThrow(NullPointerException::new);
        YearMonth currentMonth = YearMonth.now();

        // 존재하는지 확인
        MonthlySummary existingSummary = monthlySummaryRepository.findAll().get(0);
        if (existingSummary != null) {
            // 존재하면 업데이트
            existingSummary.setGoalCalories(goalCalReq.getGoalCalories());
        } else {
            // 존재하지 않으면 새로 생성
            MonthlySummary newSummary = MonthlySummary.builder()
                    .goalCalories(goalCalReq.getGoalCalories())
                    .user(user)
                    .build();
            monthlySummaryRepository.save(newSummary);
        }

        return Message.builder()
                .message("목표 칼로리 설정 완료")
                .build();

    }
}
