package com.mocum.domain.user.presentation;

import com.mocum.domain.user.application.UserService;
import com.mocum.domain.user.dto.GoalCalReq;
import com.mocum.domain.user.dto.MainRes;
import com.mocum.global.payload.ErrorResponse;
import com.mocum.global.payload.Message;
import com.mocum.global.payload.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "Mainpage", description = "Mainpage API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mainpage")
public class UserController {

    private final UserService userService;

    @Operation(summary = "메인페이지 조회", description = "메인 페이지를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메인페이지 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MainRes.class))}),
            @ApiResponse(responseCode = "400", description = "메인페이지 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping
    public ResponseCustom<MainRes> viewMypage() {
        return ResponseCustom.OK(userService.viewMainPage(1L));
    }

    @Operation(summary = "날짜 별 메인페이지 조회", description = "특정 날짜 별 메인 페이지를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "날짜 별 메인페이지 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MainRes.class))}),
            @ApiResponse(responseCode = "400", description = "날짜 별 메인페이지 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/date")
    public ResponseCustom<MainRes> viewMypageByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseCustom.OK(userService.viewMainPageByDate(1L, date));
    }

    @Operation(summary = "목표 칼로리 설정", description = "목표 칼로리를 설정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "날짜 별 메인페이지 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "날짜 별 메인페이지 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/goalCalories")
    public ResponseCustom<Message> viewMypageByDate(@RequestBody GoalCalReq goalCalReq) {
        return ResponseCustom.OK(userService.setGoalCalories(1L, goalCalReq));
    }
}
