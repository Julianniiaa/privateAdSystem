package com.example.privateadsystem.service;

import com.example.privateadsystem.model.Rating;
import com.example.privateadsystem.web.dto.RatingDto;

import java.util.List;

public interface RatingService {
    Rating saveRating(RatingDto ratingDto);
    Rating getRating(long idUserFrom, long idUserTo);
    List<Rating> getRatingIdUserFrom(long idUserFrom);
    List<Rating> getRatingIdUserTo(long idUserTo);
    List<Rating> getAllRatings();
    Rating updateRating(long id, RatingDto ratingDto);
    void deleteRating(long id);
}
