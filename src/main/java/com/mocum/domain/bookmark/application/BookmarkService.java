package com.mocum.domain.bookmark.application;

import com.mocum.domain.bookmark.domain.Bookmark;
import com.mocum.domain.bookmark.domain.repository.BookmarkRepository;
import com.mocum.domain.bookmark.dto.BookmarkResponse;
import com.mocum.domain.bookmark.dto.RegisterBookmarkReq;
import com.mocum.domain.food.domain.Food;
import com.mocum.domain.food.domain.repository.FoodRepository;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public Message registerBookmark(RegisterBookmarkReq registerBookmarkReq) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Food food = foodRepository.findById(registerBookmarkReq.getFoodId())
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .food(food)
                .build();

        bookmarkRepository.save(bookmark);

        return new Message("북마크가 성공적으로 등록되었습니다.");
    }

    @Transactional
    public Message deleteBookmark(Long foodId) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));

        Bookmark bookmark = bookmarkRepository.findByUserAndFood(user, food)
                .orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));

        bookmarkRepository.delete(bookmark);

        return new Message("북마크가 성공적으로 삭제되었습니다.");
    }

    @Transactional
    public List<BookmarkResponse> getBookmarksByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Bookmark> bookmarks = bookmarkRepository.findByUser(user);

        return bookmarks.stream()
                .map(bookmark -> new BookmarkResponse(
                        bookmark.getFood().getId(),
                        bookmark.getFood().getFoodName(),
                        bookmark.getFood().getCalories(),
                        bookmark.getFood().getCarbohydrates(),
                        bookmark.getFood().getProtein(),
                        bookmark.getFood().getFat(),
                        bookmark.getFood().getWater()
                ))
                .collect(Collectors.toList());
    }

}
