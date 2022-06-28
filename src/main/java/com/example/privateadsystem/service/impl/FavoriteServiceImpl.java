package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Favorite;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.FavoriteRepository;
import com.example.privateadsystem.repository.PostRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.FavoriteService;
import com.example.privateadsystem.web.dto.FavoriteDto;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               UserRepository userRepository,
                               PostRepository postRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Favorite saveFavorite(FavoriteDto favoriteDto) {
        if(favoriteRepository.findFavoriteByUser_IdUserAndPost_IdPost(
                favoriteDto.getIdUser(), favoriteDto.getIdPost()) == null) {
            Favorite favorite = Favorite.builder()
                    .user(userRepository.findByIdUser(favoriteDto.getIdUser()))
                    .post(postRepository.findByIdPost(favoriteDto.getIdPost()))
                    .build();
            return favoriteRepository.save(favorite);
        }
        return null;
    }

    @Override
    public List<Favorite> getAllByUser(long idUser) {
        return favoriteRepository.findFavoritesByUserIdUser(idUser);
    }

    @Override
    public void deleteFavorite(long id) {
        favoriteRepository.deleteById(id);
    }
}
