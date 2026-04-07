package com.zh.movies.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie_production")
@IdClass(MovieProductionId.class)
public class MovieProduction {

    @Id
    @Column(name = "movie_id")
    private Long movieId;

    @Id
    @Column(name = "company_id")
    private Long companyId;

    // Optional: ManyToOne mappings
    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private ProductionCompany company;
}

