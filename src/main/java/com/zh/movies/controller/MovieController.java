package com.zh.movies.controller;


import com.zh.movies.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin("http://localhost:5173")
public class MovieController {

    private final MovieRepository repo;

    public MovieController(MovieRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Page<Movie> getMovies(
            @RequestParam(defaultValue = "popular") String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return repo.findByCategory(category, PageRequest.of(page, size));
    }
}

