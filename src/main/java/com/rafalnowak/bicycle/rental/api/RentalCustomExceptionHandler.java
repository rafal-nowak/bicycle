package com.rafalnowak.bicycle.rental.api;

import com.rafalnowak.bicycle.availability.api.ErrorResponse;
import com.rafalnowak.bicycle.rental.command.domain.MethodNotAllowedException;
import com.rafalnowak.bicycle.rental.command.domain.UserRentalsAlreadyExistsException;
import com.rafalnowak.bicycle.rental.command.domain.UserRentalsNotFoundException;
import com.rafalnowak.bicycle.rental.query.facade.UserRentalsDtoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
class RentalCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserRentalsNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsNotFoundException(UserRentalsNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRentalsDtoNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsDtoNotFoundException(UserRentalsDtoNotFoundException ex) {
        return buildResponse(ex,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRentalsAlreadyExistsException.class)
    public final ResponseEntity<ErrorResponse> handleUserRentalsAlreadyExistsException(UserRentalsAlreadyExistsException ex) {
        return buildResponse(ex,  HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public final ResponseEntity<ErrorResponse> handleMethodNotAllowedException(MethodNotAllowedException ex) {
        return buildResponse(ex,  HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(IOException.class)
    public final ResponseEntity<com.rafalnowak.bicycle.availability.api.ErrorResponse> handleCommandNotSupportedException(IOException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new com.rafalnowak.bicycle.availability.api.ErrorResponse(ex.getMessage()));
    }

    private <E extends RuntimeException> ResponseEntity<com.rafalnowak.bicycle.availability.api.ErrorResponse> buildResponse(E exception, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }

}