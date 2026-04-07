package com.zh.movies.model.entity;

import com.zh.movies.dao.GenreRepository;
import com.zh.movies.model.dto.ActorDto;
import com.zh.movies.model.dto.ImageDto;
import com.zh.movies.model.dto.TrailerDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String posterUrl;
    private String posterFileName;
    private String backDropUrl;
    private LocalDate releaseDate;
    private Integer runtimeMinutes;

    private Double averageRating;
    private Integer totalReviews;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ================= RELATIONSHIPS =================
    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private MovieDetail detail;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieGenre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieActor> actors = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieDirector> directors = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieTrailer> trailers = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<MovieProduction> productions = new ArrayList<>();


    // ================= HELPER METHODS =================
    public void addDetails(String description) {
        if (description == null || description.isBlank()) return;
        MovieDetail d = new MovieDetail();
        d.setMovie(this);
        d.setDescription(description);
        this.detail = d;
    }

    public void addGenres(List<Genre> genreList) {
        if (genreList == null || genreList.isEmpty()) return;
        genres.clear();
        genreList.forEach(genre -> {
            MovieGenre mg = new MovieGenre();
            mg.setMovie(this);
            mg.setGenre(genre);
            genres.add(mg);
        });
    }


    public void addActors(List<Actor> actorEntities, List<ActorDto> actorDtos) {

        if (actorEntities == null || actorEntities.isEmpty()) return;

        actors.clear();

        for (int i = 0; i < actorEntities.size(); i++) {
            Actor actor = actorEntities.get(i);
            ActorDto dto = actorDtos.get(i);

            MovieActor ma = new MovieActor();
            ma.setMovie(this);
            ma.setActor(actor); // ✅ real entity from DB
            ma.setCharacterName(dto.getCharacterName());
            ma.setCastOrder(dto.getCastOrder());

            actors.add(ma);
        }
    }

    public void addDirectors(List<Long> directorIds) {
        if (directorIds == null || directorIds.isEmpty()) return;

        directors.clear();

        directorIds.forEach(id -> {
            MovieDirector md = new MovieDirector();

            Director director = new Director();
            director.setId(id); // OR fetch from DB

            md.setMovie(this);       // ✅ REQUIRED
            md.setDirector(director); // ✅ REQUIRED

            directors.add(md);
        });
    }

    public void addTrailers(List<TrailerDto> trailerDtos) {
        if (trailerDtos == null || trailerDtos.isEmpty()) return;
        trailers.clear();
        trailerDtos.forEach(t -> {
            MovieTrailer mt = new MovieTrailer();
            mt.setMovie(this);
            mt.setTrailerUrl(t.getTrailerUrl());
            mt.setPlatform(t.getPlatform());
            mt.setCreatedAt(LocalDateTime.now());
            trailers.add(mt);
        });
    }

    public void addImages(List<ImageDto> imageDtos) {
        if (imageDtos == null || imageDtos.isEmpty()) return;
        images.clear();
        imageDtos.forEach(i -> {
            MovieImage mi = new MovieImage();
            mi.setMovie(this);
            mi.setImageUrl(i.getImageUrl());
            mi.setType(i.getType());
            images.add(mi);
        });
    }

    public void addProductions(List<Long> companyIds) {
        if (companyIds == null || companyIds.isEmpty()) return;
        productions.clear();
        companyIds.forEach(id -> {
            MovieProduction mp = new MovieProduction();
            mp.setMovie(this);
            mp.setCompanyId(id);
            productions.add(mp);
        });
    }

    // ================= AUDIT =================
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}








