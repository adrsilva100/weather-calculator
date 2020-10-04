package com.mercadolivre.weather.dto;

public class TriangleDto {

    private PointDto point_1;
    private PointDto point_2;
    private PointDto point_3;

    public TriangleDto(final PointDto point_1, final PointDto point_2, final PointDto point_3) {
        this.point_1 = point_1;
        this.point_2 = point_2;
        this.point_3 = point_3;
    }

    public float getArea() {
        return Math.abs((float) (point_1.getX() * (point_2.getY() - point_3.getY()) + point_2.getX() * (point_3.getY() - point_1.getY()) + point_3.getX() * (point_1.getY() - point_2.getY())) / 2);
    }

    public double getPerimeter() {
        return this.point_1.getDistance(point_2) + this.point_2.getDistance(point_3) + this.point_3.getDistance(point_1);
    }

    public boolean isExistPoint(final PointDto point) {
        TriangleDto triangle_1 = new TriangleDto(point, point_2, point_3);
        TriangleDto triangle_2 = new TriangleDto(point_1, point, point_3);
        TriangleDto triangle_3 = new TriangleDto(point_1, point_2, point);

        return (triangle_1.getArea() + triangle_2.getArea() + triangle_3.getArea()) == this.getArea();
    }
}
