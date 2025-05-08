package com.rating.microservices.project.RatingService.exceptions;

import com.rating.microservices.project.RatingService.exceptions.ResourceNotFoundException;
import com.rating.microservices.project.RatingService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException( ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse build = new ApiResponse( message, true, HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiResponse>(build, HttpStatus.NOT_FOUND);
    }
     */

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerResourceNotFoundException(ResourceNotFoundException e) {

        Map map = new HashMap();
        map.put("message", e.getMessage());
        map.put("success", false);
        map.put("status", HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
