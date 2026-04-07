package com.zh.movies.service;

import com.zh.movies.model.dto.MovieDto;
import com.zh.movies.model.dto.MovieRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();
    Optional<MovieDto> getMovieById(Long Id);
    void createMovie(MovieRequestDto dto, MultipartFile posterFile);
    MovieDto updateMovie(Long id, MovieDto movieDto);
    void deleteById(Long Id);
}
