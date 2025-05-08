package com.hotel.microservices.project.HotelService.controllers;

import com.hotel.microservices.project.HotelService.entities.Hotel;
import com.hotel.microservices.project.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @PostConstruct
    public List<String> createStaffDB(){
        List<String> dbTest = Arrays.asList("Ram", "Shyam", "Seeta", "Geeta", "Krishna");
        return dbTest;
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllStaff() {
        List<String> fetchedStaffs = createStaffDB();
        return new ResponseEntity<>(fetchedStaffs, HttpStatus.OK);
    }
}
