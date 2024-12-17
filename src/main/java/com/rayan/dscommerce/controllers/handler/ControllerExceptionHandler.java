package com.rayan.dscommerce.controllers.handler;

import com.rayan.dscommerce.dto.ErrorDetails;
import com.rayan.dscommerce.dto.ValidationError;
import com.rayan.dscommerce.services.exceptions.DatabaseException;
import com.rayan.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails error = new ErrorDetails(Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorDetails> handleDatabaseException(DatabaseException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails error = new ErrorDetails(Instant.now(), status.value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(Instant.now(), status.value(), "Invalid data", request.getRequestURI());
        for (FieldError f : exception.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }
}
