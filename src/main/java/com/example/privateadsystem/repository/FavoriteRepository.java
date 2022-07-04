package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findFavoriteByUser_IdUserAndPost_IdPost(long idUser, long idPost);
    List<Favorite> findFavoritesByUserIdUser(long idUser);
}
