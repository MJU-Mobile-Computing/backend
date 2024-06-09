package com.mocum.domain.user.domain.repository;

import com.mocum.domain.user.domain.MonthlySummary;
import com.mocum.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MonthlySummaryRepository extends JpaRepository<MonthlySummary, Long> {
    Optional<MonthlySummary> findByUserAndMonth(User user, String month);

    @Query("SELECT ms FROM MonthlySummary ms WHERE ms.user = :user ORDER BY ms.createdAt DESC")
    Optional<MonthlySummary> findTopByUserOrderByCreatedAtDesc(User user);
}
