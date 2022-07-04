package com.example.privateadsystem.controller;

import com.example.privateadsystem.model.Rating;
import com.example.privateadsystem.service.RatingService;
import com.example.privateadsystem.model.dto.RatingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/ratings")
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    private final RatingService ratingService;
    RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{idUserTo}")
    public Rating createRating(@RequestBody RatingDto ratingDto,
                               @PathVariable(name = "id") long id,
                               @PathVariable(name = "idUserTo") long idUserTo) {
        logger.info("Create rating by {} to {}", id, idUserTo);
        ratingDto.setIdUserFrom(id);
        ratingDto.setIdUserTo(idUserTo);
        return ratingService.saveRating(ratingDto);
    }

    @GetMapping("/{idUserTo}")
    public Rating getRating(@PathVariable(name = "id") long id,
                            @PathVariable(name = "idUserTo") long idUserTo) {
        logger.info("Get rating from {} to {}", id, idUserTo);
        return ratingService.getRating(id, idUserTo);
    }

    @DeleteMapping("/{idUserTo}")
    public void deleteRating(@PathVariable(name = "id") long id,
                               @PathVariable(name = "idUserTo") long idUserTo) {
        logger.info("Delete rating from {} to {}", id, idUserTo);
        ratingService.deleteRating(id, idUserTo);
    }

    @GetMapping("/ratingsFrom")
    public List<Rating> getAllRatingsIdUserFrom(@PathVariable(name = "id") long id) {
        logger.info("Get all ratings by {}", id);
        return ratingService.getRatingIdUserFrom(id);
    }

    @GetMapping("/ratingsTo")
    public List<Rating> getAllRatingsIdUserTo(@PathVariable(name = "id") long id) {
        logger.info("Get all ratings to {}", id);
        return ratingService.getRatingIdUserTo(id);
    }
}
