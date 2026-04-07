package com.zh.movies.dao;

import com.zh.movies.model.entity.MovieActor;
import com.zh.movies.model.entity.MovieActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorId> {
}
