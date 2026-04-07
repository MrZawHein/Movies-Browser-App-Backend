package com.zh.movies.model.dto;

import lombok.Data;

@Data
public class ActorDto {
    private Long actorId;
    private String firstName;
    private String lastName;
    private String address;

    private String characterName;
    private Integer castOrder;
}
