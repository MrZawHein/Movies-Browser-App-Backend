package com.zh.movies.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "movie_actors")
@IdClass(MovieActorId.class)
public class MovieActor {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @Column(name = "actor_id")
    private Long actorId;

    private String characterName;
    private Integer castOrder;

    // ✅ Movie relation
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @MapsId("movieId")
    private Movie movie;

    // ✅ Actor relation (THIS IS MISSING IN YOUR CODE)
    @ManyToOne
    @JoinColumn(name = "actor_id")
    @MapsId("actorId")
    private Actor actor;
}






