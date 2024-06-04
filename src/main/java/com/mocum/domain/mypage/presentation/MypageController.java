package com.mocum.domain.mypage.presentation;

import com.mocum.domain.food.dto.FoodSearchRes;
import com.mocum.domain.mypage.application.MypageService;
import com.mocum.domain.mypage.dto.MypageEditReq;
import com.mocum.domain.mypage.dto.MypageRes;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "Mypage", description = "Mypage API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;

    @Operation(summary = "마이페이지 조회", description = "내 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "마이페이지 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MypageRes.class))}),
            @ApiResponse(responseCode = "400", description = "마이페이지 조회 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping
    public ResponseCustom<MypageRes> viewMypage() {
        return ResponseCustom.OK(mypageService.viewMypage());
    }

    @Operation(summary = "마이페이지 수정", description = "내 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 정보 수정 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "내 정보 수정 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PatchMapping
    public ResponseCustom<Message> editMypage(
            @Parameter(description = "MypageEditReq 스키마를 참고해주세요.") @RequestBody MypageEditReq mypageEditReq
    ) {
        return ResponseCustom.OK(mypageService.editMypage(mypageEditReq));
    }
}
