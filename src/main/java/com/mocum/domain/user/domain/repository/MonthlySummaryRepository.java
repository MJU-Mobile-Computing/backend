package com.mocum.domain.user.domain.repository;

import com.mocum.domain.user.domain.MonthlySummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlySummaryRepository extends JpaRepository<MonthlySummary, Long> {
}
