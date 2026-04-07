package com.zh.movies.dao;

import com.zh.movies.model.entity.MovieTrailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTrailerRepository extends JpaRepository<MovieTrailer, Long> {
}
