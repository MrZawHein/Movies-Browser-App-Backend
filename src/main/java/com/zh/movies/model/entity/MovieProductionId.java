package com.zh.movies.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieProductionId implements Serializable {
    private Long movieId;
    private Long companyId;
}
