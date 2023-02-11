package com.robsil.erommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robsil.erommerce.model.exception.*;
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
    public ResponseEntity<String> onEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpConflictException.class)
    public ResponseEntity<String> onHttpConflictException(HttpConflictException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> onForbiddenException(ForbiddenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> onMethodArgumentNotValidException(MethodArgumentNotValidException e) throws JsonProcessingException {
        var response = new ValidationErrorResponse();

        e.getBindingResult().getFieldErrors().forEach(error -> response.getViolations().add(new Violation(error.getField(), error.getDefaultMessage())));

        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.BAD_REQUEST);
    }

}
