package com.user.microservices.project.UserService.services.impl;

import com.user.microservices.project.UserService.entities.Hotel;
import com.user.microservices.project.UserService.entities.Rating;
import com.user.microservices.project.UserService.entities.User;
import com.user.microservices.project.UserService.exceptions.ResourceNotFoundException;
import com.user.microservices.project.UserService.external.services.HotelService;
import com.user.microservices.project.UserService.external.services.RatingService;
import com.user.microservices.project.UserService.repositories.UserRepository;
import com.user.microservices.project.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingService ratingService;

    @Autowired
    HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User theUser =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on the server."));

        List<Rating> ratings = ratingService.getRating(theUser.getUserId());
        logger.info("The ratings fetched: {} ", ratings);

        List<Rating> ratingList = ratings.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        theUser.setRatings(ratingList);

        return theUser;
    }

    @Override
    public User saveUserRating(User user, Rating rating) {
        List<Rating> ratings = ratingService.getRating(user.getUserId());
        ResponseEntity<Rating> newRating = ratingService.createRating(rating);
        logger.info("The ratings fetched: {} ", ratings);
        ratings.add(newRating.getBody());
        logger.info("The updated ratings is: {} ", ratings);

        user.setRatings(ratings);

        return user;
    }
}
