package com.mercadolivre.weather.dto;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PointDtoTest {

    @Test
    public void distanciaAunPuntoTest() {
        TriangleDto triangleDto = new TriangleDto(new PointDto(0, 0), new PointDto(0, 3), new PointDto(4, 0));
        assertEquals(6, triangleDto.getArea(), 0);
    }
}
