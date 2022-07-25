package com.ziola.recruitmenttask.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ObjectToRentIncorrectDateException.class)
    public ResponseEntity<ErrorMessage> objectToRentIncorrectDate(ObjectToRentIncorrectDateException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TenantNotFoundException.class)
    public ResponseEntity<ErrorMessage> tenantNotFound(TenantNotFoundException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDate.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoReservationFoundException.class)
    public ResponseEntity<ErrorMessage> noReservationFound(NoReservationFoundException e) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(e.getMessage());
        error.setTimestamp(LocalDate.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
