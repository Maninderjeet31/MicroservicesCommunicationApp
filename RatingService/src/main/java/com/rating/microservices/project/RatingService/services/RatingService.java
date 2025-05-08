package com.rating.microservices.project.RatingService.services;

import com.rating.microservices.project.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //fetch all ratings
    List<Rating> getAllRatings();

    //fetch single ratings
    Rating getRating(String ratingId);

    //TODO: Delete
    //TODO: Update

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

    Rating createRating(Rating rating);
}
