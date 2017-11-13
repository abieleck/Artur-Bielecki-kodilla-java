package com.kodilla.testing.shape;

import java.security.InvalidParameterException;

public class Circle implements Shape {

    private double radius;

    public Circle(double radius) {
        if (radius < 0) {
            throw new InvalidParameterException("Attempt to instantiate Circle object with negative radius.");
        }
        this.radius = radius;
    }

    @Override
    public String getShapeName() {
        return "Circle";
    }

    @Override
    public double getField() {
        return Math.PI * radius * radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(radius);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                "; field=" + getField() +
                '}';
    }
}
