package com.example.privateadsystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {

    private Long idRating;

    private int value;

    private long idUserTo;

    private long idUserFrom;
}
