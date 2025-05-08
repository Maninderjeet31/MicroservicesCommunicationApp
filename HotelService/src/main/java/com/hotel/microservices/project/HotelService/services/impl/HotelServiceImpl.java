package com.hotel.microservices.project.HotelService.services.impl;

import com.hotel.microservices.project.HotelService.services.HotelService;
import com.hotel.microservices.project.HotelService.entities.Hotel;
import com.hotel.microservices.project.HotelService.exceptions.ResourceNotFoundException;
import com.hotel.microservices.project.HotelService.repositories.HotelRepository;
import com.hotel.microservices.project.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on the server."));
    }
}
