package com.mocum.domain.exercise.domain.repository;

import com.mocum.domain.exercise.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
