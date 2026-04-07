package com.zh.movies.dao;

import com.zh.movies.model.entity.MovieProduction;
import com.zh.movies.model.entity.MovieProductionId;
import com.zh.movies.model.entity.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieProductionRepository extends JpaRepository<MovieProduction, MovieProductionId> {
}
