package com.kodilla.testing.shape;

import java.security.InvalidParameterException;

public class Square implements Shape {

    private double side;

    public Square(double side) {
        if (side < 0) {
            throw new InvalidParameterException("Attempt to instantiate Square object with negative side.");
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public String getShapeName() {
        return "S";
    }

    @Override
    public double getField() {
        return side * side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        return Double.compare(square.side, side) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(side);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                "; field=" + getField() +
                '}';
    }
}
