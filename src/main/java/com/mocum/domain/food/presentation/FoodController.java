package com.mocum.domain.food.presentation;

import com.mocum.domain.food.application.FoodService;
import com.mocum.domain.food.dto.FoodSearchRes;
import com.mocum.global.payload.ErrorResponse;
import com.mocum.global.payload.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Food", description = "Food API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "음식 검색", description = "데이터베이스에서 음식을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "음식 검색 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FoodSearchRes.class))}),
            @ApiResponse(responseCode = "400", description = "음식 검색 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping()
    public ResponseCustom<FoodSearchRes> foodSearch(
            @Parameter(description = "foods/?title=라면 형식으로 입력해주세요") @RequestParam String foodName
    ) {
        return ResponseCustom.OK(foodService.searchFood(foodName));
    }
}
