package com.mocum.domain.bookmark.presentation;


import com.mocum.domain.bookmark.application.BookmarkService;
import com.mocum.domain.bookmark.dto.RegisterBookmarkReq;
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

@Tag(name = "Bookmark", description = "Bookmark API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "음식 북마크 등록", description = "음식을 북마크에 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "북마크 등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "북마크 등록 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/register")
    public ResponseCustom<Message> registerBookmark(
            @Parameter(description = "RegisterBookmarkReq 스키마를 참고해주세요.") @RequestBody RegisterBookmarkReq registerBookmarkReq
    ) {
        return ResponseCustom.OK(bookmarkService.registerBookmark(registerBookmarkReq));
    }


    @Operation(summary = "음식 북마크 삭제", description = "음식을 북마크에서 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "북마크 삭제 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "400", description = "북마크 삭제 실패", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/delete")
    public ResponseCustom<Message> deleteBookmark(
            @RequestParam Long foodId
    ) {
        return ResponseCustom.OK(bookmarkService.deleteBookmark(foodId));
    }
}
