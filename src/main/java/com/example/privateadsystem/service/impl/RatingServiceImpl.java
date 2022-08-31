package com.example.privateadsystem.service.impl;

import com.example.privateadsystem.model.Rating;
import com.example.privateadsystem.model.User;
import com.example.privateadsystem.repository.RatingRepository;
import com.example.privateadsystem.repository.UserRepository;
import com.example.privateadsystem.service.RatingService;
import com.example.privateadsystem.model.dto.RatingDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public void setAvgRating(User user, long idUserTo) {
        List<Rating> ratingList = ratingRepository.findRatingsByUserTo_IdUser(idUserTo);
        double sumRating = 0;
        for (Rating r : ratingList) {
            sumRating += r.getValue();
        }
        user.setAvgRating(sumRating / ratingList.size());

        userRepository.save(user);
    }

    @Override
    public Rating saveRating(RatingDto ratingDto) {
        Rating ratingFound = ratingRepository.findRatingByUserFrom_IdUserAndUserTo_IdUser(
                ratingDto.getIdUserFrom(),
                ratingDto.getIdUserTo());
        if (ratingFound.getIdRating() == null) {
            User user = userRepository.findByIdUser(ratingDto.getIdUserTo());
            Rating rating = Rating.builder().value(ratingDto.getValue())
                    .dateRating(LocalDateTime.now())
                    .userFrom(userRepository.findByIdUser(ratingDto.getIdUserFrom()))
                    .userTo(user)
                    .build();
            Rating ratingSaved = ratingRepository.save(rating);

            setAvgRating(user, ratingDto.getIdUserTo());

            return ratingSaved;
        }
        else return updateRating(ratingFound.getIdRating(), ratingDto);
    }

    @Override
    public Rating getRating(long idUserFrom, long idUserTo) {
        return ratingRepository.findRatingByUserFrom_IdUserAndUserTo_IdUser(
                idUserFrom, idUserTo);
    }

    @Override
    public List<Rating> getRatingIdUserFrom(long idUserFrom) {
        return ratingRepository.findRatingsByUserFrom_IdUser(idUserFrom);
    }

    @Override
    public List<Rating> getRatingIdUserTo(long idUserTo) {
        return ratingRepository.findRatingsByUserTo_IdUser(idUserTo);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating updateRating(long id, RatingDto ratingDto) {
        Rating rating = ratingRepository.findByIdRating(id);
        rating.setDateRating(LocalDateTime.now());
        rating.setValue(ratingDto.getValue());
        Rating ratingSaved = ratingRepository.save(rating);
        setAvgRating(userRepository.findByIdUser(ratingDto.getIdUserTo()), ratingDto.getIdUserTo());
        return ratingSaved;
    }

    @Override
    public void deleteRating(long id, long idUserTo) {
        Rating ratingFound = ratingRepository.findRatingByUserFrom_IdUserAndUserTo_IdUser(id, idUserTo);
        ratingRepository.deleteById(ratingFound.getIdRating());
        setAvgRating(ratingFound.getUserTo(), ratingFound.getUserTo().getIdUser());
    }
}
