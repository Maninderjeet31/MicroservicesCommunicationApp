package com.hotel.microservices.project.HotelService.services;

import com.hotel.microservices.project.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel saveHotel(Hotel hotel);

    //fetch all hotels
    List<Hotel> getAllHotels();

    //fetch single hotel
    Hotel getHotel(String hotelId);

    //TODO: Delete
    //TODO: Update

}
