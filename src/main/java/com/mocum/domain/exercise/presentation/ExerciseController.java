package com.mocum.domain.exercise.presentation;

import com.mocum.domain.exercise.application.ExerciseService;
import com.mocum.domain.exercise.dto.RegisterExerciseReq;
import com.mocum.domain.food.dto.RegisterMealReq;
import com.mocum.global.payload.ErrorResponse;
import com.mocum.global.payload.Message;
import com.mocum.global.payload.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Exercise", description = "Exercise API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(summary = "운동 시간 등록", description = "데이터베이스에서 운동 시간을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "운동 시간 등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "운동 시간 등록 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/register")
    public ResponseCustom<Message> registerExercise(
            @Parameter(description = "RegisterExerciseReq 스키마를 참고해주세요.") @RequestBody RegisterExerciseReq registerExerciseReq
    ) {
        return ResponseCustom.OK(exerciseService.registerExercise(registerExerciseReq));
    }
}
