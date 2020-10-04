package com.mercadolivre.weather.dto;

import lombok.Getter;

@Getter
public class PointDto {

    private double x;
    private double y;

    public PointDto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(PointDto pointDto) {
        return Math.sqrt(Math.pow(pointDto.getX() - this.x, 2) + Math.pow(pointDto.getY() - this.y, 2));
    }
}
