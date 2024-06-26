package com.mocum.domain.exercise.domain.repository;

import com.mocum.domain.exercise.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT SUM(e.exerciseHour) FROM Exercise e WHERE e.user.id = :id")
    Optional<Long> findTotalExerciseHoursByUserId(long id);

    @Query("SELECT SUM(e.exerciseHour) FROM Exercise e WHERE e.user.id = :id AND e.createdAt BETWEEN :startDate AND :endDate")
    Optional<Long> findDailyExerciseHoursByUserId(@Param("id") long id, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
