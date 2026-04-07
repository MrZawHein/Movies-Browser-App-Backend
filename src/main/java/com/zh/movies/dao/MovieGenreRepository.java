package com.zh.movies.dao;

import com.zh.movies.model.entity.MovieGenre;
import com.zh.movies.model.entity.MovieGenreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenreId> {
}
