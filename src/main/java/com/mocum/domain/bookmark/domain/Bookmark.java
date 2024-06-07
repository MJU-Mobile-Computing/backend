package com.mocum.domain.bookmark.domain;

import com.mocum.domain.common.BaseEntity;
import com.mocum.domain.food.domain.Food;
import com.mocum.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bookmark")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @Builder
    public Bookmark(User user, Food food) {
        this.user = user;
        this.food = food;
    }
}
