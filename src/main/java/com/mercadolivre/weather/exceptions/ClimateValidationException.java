package com.mercadolivre.weather.exceptions;

public class ClimateValidationException extends RuntimeException {

    public ClimateValidationException(final String message) {
        super(message);
    }
}
