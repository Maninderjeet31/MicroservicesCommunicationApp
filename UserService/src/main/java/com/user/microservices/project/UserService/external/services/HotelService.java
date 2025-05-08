package com.user.microservices.project.UserService.external.services;

import com.user.microservices.project.UserService.entities.Hotel;
import com.user.microservices.project.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

    //POST Hotel
    @PostMapping("/hotels/")
    public Hotel createHotel(Hotel hotel);

    //PUT Hotel
    @PostMapping("/hotels/{hotelId}")
    public Hotel updateHotel(@PathVariable String hotelId, Hotel hotel);

    //Delete Hotel
    @DeleteMapping("/hotels/{hotelId}")
    public void deleteHotel(@PathVariable String hotelId);
}
