package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.exception.DataBaseException;
import com.example.privateadsystem.model.Favorite;
import com.example.privateadsystem.repository.FavoriteRepository;
import com.example.privateadsystem.repository.PostRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.FavoriteService;
import com.example.privateadsystem.model.dto.FavoriteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);

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
        if (favoriteRepository.findFavoriteByUser_IdUserAndPost_IdPost(
                favoriteDto.getIdUser(), favoriteDto.getIdPost()) == null) {
            Favorite favorite = Favorite.builder()
                    .user(userRepository.findByIdUser(favoriteDto.getIdUser()))
                    .post(postRepository.findByIdPost(favoriteDto.getIdPost()))
                    .build();
            return favoriteRepository.save(favorite);
        }
        logger.info("This post {} has already been liked", favoriteDto.getIdPost());
        throw new DataBaseException("This post has already been liked");
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
