package com.workintech.challenge.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleErrors(CourseException exception){
        CourseErrorResponse response = new CourseErrorResponse(exception.getStatus().value(), exception.getMessage(), System.currentTimeMillis());
       log.error(exception.getMessage());
        return new ResponseEntity<>(response,exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleErrors(Exception exception){
        CourseErrorResponse response = new CourseErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        log.error(exception.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
