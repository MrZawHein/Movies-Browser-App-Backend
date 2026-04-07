package com.zh.movies.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "movie_genres")
@IdClass(MovieGenreId.class)
public class MovieGenre {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @Column(name = "genre_id")
    private Long genreId;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Movie movie;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;
}







