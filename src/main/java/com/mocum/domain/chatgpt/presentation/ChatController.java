package com.mocum.domain.chatgpt.presentation;

import com.mocum.domain.chatgpt.application.ChatService;
import com.mocum.domain.food.dto.FoodSearchRes;
import com.mocum.global.payload.ErrorResponse;
import com.mocum.global.payload.ResponseCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ChatController {

    private final ChatService chatService;


    @Operation(summary = "Chatgpt 검색", description = "Chatgpt에서 음식을 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "gpt 검색 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseCustom.class))}),
            @ApiResponse(responseCode = "400", description = "gpt 검색 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/chat-gpt")
    public ResponseCustom<String> test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}
