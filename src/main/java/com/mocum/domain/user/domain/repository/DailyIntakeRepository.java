package com.mocum.domain.user.domain.repository;

import com.mocum.domain.user.domain.DailyIntakes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyIntakeRepository extends JpaRepository<DailyIntakes, Long> {
}
