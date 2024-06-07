package com.mocum.domain.exercise.domain;

import com.mocum.domain.common.BaseEntity;
import com.mocum.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exercise")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Exercise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "exerciseHour")
    private Integer exerciseHour;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    @Builder
    public Exercise(Integer exerciseHour, User user) {
        this.exerciseHour = exerciseHour;
        this.user = user;
    }
}
