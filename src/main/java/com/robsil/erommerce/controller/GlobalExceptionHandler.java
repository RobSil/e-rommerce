package com.robsil.erommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.exception.HttpConflictException;
import com.robsil.erommerce.model.exception.ValidationErrorResponse;
import com.robsil.erommerce.model.exception.Violation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private ObjectMapper objectMapper;

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> onEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpConflictException.class)
    ResponseEntity<String> onHttpConflictException(HttpConflictException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) throws JsonProcessingException {
        var response = new ValidationErrorResponse();

        e.getBindingResult().getFieldErrors().forEach(error -> response.getViolations().add(new Violation(error.getField(), error.getDefaultMessage())));

        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
    }

}
