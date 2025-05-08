package com.user.microservices.project.UserService.external.services;

import com.user.microservices.project.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRating(@PathVariable String userId);

    //POST Rating
    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating);

    //PUT Rating
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);

    //Delete Rating
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);

}
