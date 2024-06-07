package com.mocum.domain.bookmark.application;

import com.mocum.domain.bookmark.domain.Bookmark;
import com.mocum.domain.bookmark.domain.repository.BookmarkRepository;
import com.mocum.domain.bookmark.dto.RegisterBookmarkReq;
import com.mocum.domain.food.domain.Food;
import com.mocum.domain.food.domain.repository.FoodRepository;
import com.mocum.domain.user.domain.User;
import com.mocum.domain.user.domain.repository.UserRepository;
import com.mocum.global.payload.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
