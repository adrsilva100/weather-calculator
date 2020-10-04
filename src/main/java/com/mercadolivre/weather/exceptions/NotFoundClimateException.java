package com.mercadolivre.weather.exceptions;

public class NotFoundClimateException extends RuntimeException {

    public NotFoundClimateException(final String message) {
        super(message);
    }
}
