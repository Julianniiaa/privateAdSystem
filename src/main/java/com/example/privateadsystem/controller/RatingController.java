package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Rating;
import com.example.privateadsystem.service.RatingService;
import com.example.privateadsystem.web.dto.RatingDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public Rating createRating(@RequestBody RatingDto ratingDto) {
        return ratingService.saveRating(ratingDto);
    }
}
