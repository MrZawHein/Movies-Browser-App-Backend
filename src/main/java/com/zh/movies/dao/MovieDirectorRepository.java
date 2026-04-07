package com.zh.movies.dao;

import com.zh.movies.model.entity.MovieDirector;
import com.zh.movies.model.entity.MovieDirectorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDirectorRepository extends JpaRepository<MovieDirector, MovieDirectorId> {
}
