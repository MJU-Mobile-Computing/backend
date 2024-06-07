package com.mocum.domain.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkResponse {
    private Long foodId;
    private String foodName;
    private double calories;
    private double carbohydrates;
    private double protein;
    private double fat;
    private double water;
}