package com.hotel.microservices.project.HotelService.repositories;

import com.hotel.microservices.project.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

}
