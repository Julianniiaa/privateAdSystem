package com.example.privateadsystem.repository;

import com.example.privateadsystem.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findRatingsByUserTo_IdUser(long id);
    Rating findRatingByUserFrom_IdUserAndUserTo_IdUser(long idFrom, long idTo);
    Rating findByIdRating(long id);
    List<Rating> findRatingsByUserFrom_IdUser(long id);
}
