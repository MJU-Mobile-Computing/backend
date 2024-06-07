package com.mocum.domain.exercise.application;

import com.mocum.domain.exercise.domain.Exercise;
import com.mocum.domain.exercise.domain.repository.ExerciseRepository;
import com.mocum.domain.exercise.dto.RegisterExerciseReq;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    @Transactional
    public Message registerExercise(RegisterExerciseReq registerExerciseReq) {
        User user = userRepository.findById(1L).get();

        Exercise ex = Exercise.builder()
                .exerciseHour(registerExerciseReq.getExerciseHour())
                .user(user)
                .build();

        exerciseRepository.save(ex);

        return Message.builder()
                .message("운동 시간이 등록되었습니다.")
                .build();
    }
}
