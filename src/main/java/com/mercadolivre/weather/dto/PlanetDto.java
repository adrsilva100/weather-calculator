package com.mercadolivre.weather.dto;

import lombok.Getter;

@Getter
public class PlanetDto {

    private String name;
    private float distance;
    private int speed;
    private boolean clockwise;

    public PlanetDto(final String name, final float distance, final int speed) {
        this.name = name;
        this.distance = distance;
        this.speed = speed;
    }

    public PlanetDto(final String name, final float distance, final int speed, final boolean clockwise) {
        this.name = name;
        this.distance = distance;
        this.speed = speed;
        this.clockwise = clockwise;
    }

    public PointDto getPoint(final int day) {
        double angle = (day * this.speed * (clockwise ? 1 : -1)) % 360;
        double x = Math.cos(Math.toRadians(angle)) * this.distance;
        double y = Math.sin(Math.toRadians(angle)) * this.distance;

        return new PointDto(x, y);
    }
}
