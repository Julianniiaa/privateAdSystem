package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Favorite;
import com.example.privateadsystem.web.dto.FavoriteDto;

import java.util.List;

public interface FavoriteService {
    Favorite saveFavorite(FavoriteDto favoriteDto);
    List<Favorite> getAllByUser(long idUser);
    void deleteFavorite(long id);
}
