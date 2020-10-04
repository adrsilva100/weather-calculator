package com.mercadolivre.weather.dto;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleDtoTest {

    @Test
    public void areaTest() {
        TriangleDto triangleDto = new TriangleDto(new PointDto(0, 0), new PointDto(0, 3), new PointDto(4, 0));
        assertEquals(6, triangleDto.getArea(), 0);
    }

    @Test
    public void perimetroTest() {
        TriangleDto triangleDto = new TriangleDto(new PointDto(0, 0), new PointDto(0, 3), new PointDto(4, 0));
        assertEquals(12, triangleDto.getPerimeter(), 0);
    }

    @Test
    public void contieneUnPuntoTest() {
        TriangleDto triangleDto = new TriangleDto(new PointDto(0, 0), new PointDto(0, 3), new PointDto(4, 0));
        assertTrue(triangleDto.isExistPoint(new PointDto(0, 1)));
    }

}
