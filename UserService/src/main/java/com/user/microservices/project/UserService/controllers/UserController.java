package com.user.microservices.project.UserService.controllers;

import com.user.microservices.project.UserService.entities.Rating;
import com.user.microservices.project.UserService.entities.User;
import com.user.microservices.project.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    int retryCount = 1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        System.out.printf("Retry count %d", retryCount);
        User fetchedUser = userService.getUser(userId);
        return ResponseEntity.ok(fetchedUser);
    }

    //fallback for ratingHotelBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e){
        System.out.println("Fallback is executed, service is down: " + e.getMessage());
        User user = User.builder()
                .userId("131234")
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This is dummy user created because service is down")
                .build();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> fetchedUsers = userService.getAllUsers();
        return ResponseEntity.ok(fetchedUsers);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUserRating(@RequestBody Rating rating) {
        User theUser = userService.getUser(rating.getUserId());
        userService.saveUserRating(theUser, rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(theUser);
    }
}
