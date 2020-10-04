package com.mercadolivre.weather.exceptions;

import com.mercadolivre.weather.dto.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WheaterExceptionHandler {

    @ExceptionHandler(value = NotFoundClimateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleNotFoundClimateException(final NotFoundClimateException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = ClimateValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<MessageResponseDTO> handleClimateValidationException(final ClimateValidationException ex) {
        return new ResponseEntity<>(new MessageResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
