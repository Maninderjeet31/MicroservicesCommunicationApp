package com.user.microservices.project.UserService.services;

import com.user.microservices.project.UserService.entities.Rating;
import com.user.microservices.project.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //fetch all Users
    List<User> getAllUsers();

    //fetch single User
    User getUser(String userId);

    User saveUserRating(User user, Rating rating);

    //TODO: Delete
    //TODO: Update

}
