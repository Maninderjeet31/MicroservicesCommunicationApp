package com.user.microservices.project.UserService.exceptions;

import com.user.microservices.project.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException( ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse build = new ApiResponse( message, true, HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApiResponse>(build, HttpStatus.NOT_FOUND);
    }
}
