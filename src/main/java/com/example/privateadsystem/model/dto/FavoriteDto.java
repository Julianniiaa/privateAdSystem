package com.example.privateadsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FavoriteDto {

    private Long idFavorite;

    private Long idPost;

    private Long idUser;
}
