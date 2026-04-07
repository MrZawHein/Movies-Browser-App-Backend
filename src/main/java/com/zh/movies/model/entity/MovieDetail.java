package com.zh.movies.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movie_detail")
public class MovieDetail {

    @Id
    private Long movieId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private Movie movie;

    @Column(name = "description")
    private String description;
}



