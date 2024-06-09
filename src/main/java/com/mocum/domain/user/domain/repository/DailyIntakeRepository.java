package com.mocum.domain.user.domain.repository;

import com.mocum.domain.user.domain.DailyIntakes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyIntakeRepository extends JpaRepository<DailyIntakes, Long> {
    Optional<DailyIntakes> findByUserId(long l);

    @Query("SELECT SUM(d.calories), SUM(d.carbohydrates), SUM(d.protein), SUM(d.fat) " +
            "FROM DailyIntakes d " +
            "WHERE d.user.id = :userId AND d.createdAt BETWEEN :startDate AND :endDate")
    List<Object[]> findMonthlyIntakeByUserId(@Param("userId") Long userId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(d.calories), SUM(d.carbohydrates), SUM(d.protein), SUM(d.fat) " +
            "FROM DailyIntakes d " +
            "WHERE d.user.id = :userId AND d.createdAt BETWEEN :startDate AND :endDate")
    List<Object[]> findMonthlyIntakeByUserId2(@Param("userId") Long userId,
                                             @Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(d.calories), SUM(d.carbohydrates), SUM(d.protein), SUM(d.fat) " +
            "FROM DailyIntakes d " +
            "WHERE d.user.id = :userId AND d.createdAt BETWEEN :startDate AND :endDate")
    List<Object[]> findDailyIntakeByUserId(@Param("userId") Long userId,
                                           @Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
}
