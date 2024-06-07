package com.mocum.domain.bookmark.domain.repository;

import com.mocum.domain.bookmark.domain.Bookmark;
import com.mocum.domain.food.domain.Food;
import com.mocum.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndFood(User user, Food food);
}
