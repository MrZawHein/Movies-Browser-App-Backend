package com.zh.movies.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "movie_directors")
@IdClass(MovieDirectorId.class)
public class MovieDirector {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @Column(name = "director_id")
    private Long directorId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @MapsId("directorId")
    private Director director;
}




