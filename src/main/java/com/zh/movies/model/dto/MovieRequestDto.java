package com.zh.movies.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovieRequestDto {

    private String title;
    private LocalDate releaseDate;
    private Integer runtimeMinutes;

    private String posterUrl;
    private String backdropUrl;

    private String description;

    private List<Long> genreIds;
    private List<Long> directorIds;

    private List<ActorDto> actors;

    private List<TrailerDto> trailers;
    private List<ImageDto> images;

    private List<Long> productionCompanyIds;
}
