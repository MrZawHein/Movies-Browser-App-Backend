package com.zh.movies.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;
    private String title;
    private String posterFileName;
    private String posterURL;
    private LocalDate releaseDate;
    private int runtimeMinutes;
    private double averageRating;
    private int totalReviews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

