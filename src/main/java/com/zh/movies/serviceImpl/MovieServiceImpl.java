package com.zh.movies.serviceImpl;

import com.zh.movies.dao.*;
import com.zh.movies.model.dto.MovieDto;
import com.zh.movies.model.dto.MovieRequestDto;
import com.zh.movies.model.entity.*;
import com.zh.movies.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final ProductionCompanyRepository productionCompanyRepository;
    private final GenreRepository genreRepository;

    private static final String UPLOAD_DIR = "D:/Movies-Videos-Images/images/";
    private static final String IMAGE_BASE_URL = "http://localhost:8082/images/";
    private static final String DEFAULT_IMAGE = "default-movie.jpeg";

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieById(Long id) {
        return movieRepository.findById(id).map(this::mapToDto);
    }

    @Override
    @Transactional
    public void createMovie(MovieRequestDto dto, MultipartFile posterFile) {

        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setRuntimeMinutes(dto.getRuntimeMinutes());
        movie.setBackDropUrl(dto.getBackdropUrl());

        // ================= POSTER =================
        if (posterFile != null && !posterFile.isEmpty()) {
            String fileName = savePosterFile(posterFile);
            movie.setPosterFileName(fileName);
            movie.setPosterUrl("/images/" + fileName);
        } else if (dto.getPosterUrl() != null && !dto.getPosterUrl().isBlank()) {
            movie.setPosterUrl(dto.getPosterUrl());
        } else {
            movie.setPosterUrl("/images/" + DEFAULT_IMAGE);
        }

        // ================= SAVE MOVIE =================
        movie = movieRepository.save(movie);

        // ================= RELATIONS =================
        movie.addDetails(dto.getDescription());

        if (dto.getGenreIds() != null) {
            List<Genre> genres = dto.getGenreIds().stream()
                    .map(id -> genreRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Genre not found: " + id)))
                    .toList();

            movie.addGenres(genres);
        }

        if (dto.getActors() != null) {
            List<Actor> actors = dto.getActors().stream()
                    .map(a -> actorRepository.findById(a.getActorId())
                            .orElseThrow(() -> new RuntimeException("Actor not found: " + a.getActorId())))
                    .toList();

            movie.addActors(actors, dto.getActors());
        }

        movie.addDirectors(dto.getDirectorIds());
        movie.addTrailers(dto.getTrailers());
        movie.addImages(dto.getImages());
        movie.addProductions(dto.getProductionCompanyIds());

        // ================= FINAL SAVE =================
        movieRepository.save(movie);
    }

    // ================= FILE SAVE =================
    private String savePosterFile(MultipartFile file) {
        try {
            File dir = new File(UPLOAD_DIR);

            if (!dir.exists()) {
                dir.mkdirs(); // create folder
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            File dest = new File(dir, fileName);
            file.transferTo(dest);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    // ================= DTO MAPPING =================
    private MovieDto mapToDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRuntimeMinutes(movie.getRuntimeMinutes());
        dto.setCreatedAt(movie.getCreatedAt());
        dto.setUpdatedAt(movie.getUpdatedAt());

        dto.setAverageRating(movie.getAverageRating() != null ? movie.getAverageRating() : 0);
        dto.setTotalReviews(movie.getTotalReviews() != null ? movie.getTotalReviews() : 0);

        // Poster URL
        if (movie.getPosterFileName() != null && !movie.getPosterFileName().isBlank()) {
            dto.setPosterURL(IMAGE_BASE_URL + movie.getPosterFileName());
        } else {
            dto.setPosterURL(IMAGE_BASE_URL + DEFAULT_IMAGE);
        }

        dto.setPosterFileName(movie.getPosterFileName());
        return dto;
    }


    @Override
    public MovieDto updateMovie(Long id, MovieDto dto) {

        Optional<Movie> opt = movieRepository.findById(id);
        if (opt.isEmpty()) return null;

        Movie movie = opt.get();

        // ✅ Update fields from DTO
        movie.setTitle(dto.getTitle());
        movie.setPosterFileName(dto.getPosterFileName());
        movie.setPosterUrl(dto.getPosterURL());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setRuntimeMinutes(dto.getRuntimeMinutes());
        movie.setAverageRating(dto.getAverageRating());
        movie.setTotalReviews(dto.getTotalReviews());

        // ❗ DO NOT overwrite createdAt
        // movie.setCreatedAt(dto.getCreatedAt()); ❌

        // ✅ Update timestamp
        movie.setUpdatedAt(LocalDateTime.now());

        movieRepository.save(movie);

        return mapToDto(movie);
    }



    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

}



