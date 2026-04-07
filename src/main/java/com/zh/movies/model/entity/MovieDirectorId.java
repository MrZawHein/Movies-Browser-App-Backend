package com.zh.movies.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDirectorId implements Serializable {
    private Long movieId;
    private Long directorId;
}
