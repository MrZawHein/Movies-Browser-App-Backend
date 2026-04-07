package com.zh.movies.apiController;

import com.zh.movies.model.dto.MovieDto;
import com.zh.movies.model.dto.MovieRequestDto;
import com.zh.movies.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:5173")
public class MoviesApiController {

    @Autowired
    MovieService movieService;

    private static final String IMAGE_DIR_URL = "http://localhost:8082/images/";
    private static final String DEFAULT_IMAGE = "default-movie.jpeg";

    // ✅ Get all movies
    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createMovie(
            @RequestPart("movie") MovieRequestDto dto,
            @RequestPart(value = "posterFile", required = false) MultipartFile posterFile
    ) {
        movieService.createMovie(dto, posterFile);
        return ResponseEntity.ok("Movie created successfully");
    }

    // ✅ Get single movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long movieId) {

        return movieService.getMovieById(movieId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update movie by ID
    @PutMapping("/{movieId}")
    public ResponseEntity<Object> updateMovie(
            @PathVariable Long movieId,
            @RequestBody @Valid MovieDto movieDto,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        MovieDto updatedMovie = movieService.updateMovie(movieId, movieDto);

        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMovie);
    }

    // ✅ Delete movie by ID
    @DeleteMapping("/{movieId}")
    ResponseEntity<Object> deleteMovies(@PathVariable Long movieId){
        try {
            this.movieService.deleteById(movieId);
            return ResponseEntity.ok().body(null);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }

    }
}
