package com.kodilla.testing.shape;

import java.security.InvalidParameterException;

public class Triangle implements Shape {

    private double sideMin;
    private double sideMid;
    private double sideMax;

    public Triangle(double sideA, double sideB, double sideC) {

        // sort sides, that will make equals and hashCode easier to implement
        double minTmp;
        double maxTmp;

        if (sideA > sideB) {
            minTmp = sideB;
            maxTmp = sideA;
        } else {
            minTmp = sideA;
            maxTmp = sideB;
        }
        if (sideC < minTmp) {
            sideMin = sideC;
            sideMid = minTmp;
            sideMax = maxTmp;
        } else if (sideC > maxTmp) {
            sideMin = minTmp;
            sideMid = maxTmp;
            sideMax = sideC;
        } else {
            sideMin = minTmp;
            sideMid = sideC;
            sideMax = maxTmp;
        }

        if (sideMin + sideMid < sideMax) {
            throw new InvalidParameterException("Attempt to instantiate Triangle object with sides not satisfying Triangle inequality.");
        }

    }

    @Override
    public String getShapeName() {
        return "Triangle";
    }

    @Override
    public double getField() {
        //Heron's formula
        double s = (sideMin + sideMid + sideMax)/2;
        return Math.sqrt(s * (s - sideMin) * (s - sideMid) * (s - sideMax));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;
        if (Double.compare(triangle.sideMin, sideMin) != 0) return false;
        if (Double.compare(triangle.sideMid, sideMid) != 0) return false;
        return Double.compare(triangle.sideMax, sideMax) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(sideMin);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sideMid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sideMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "sideMin=" + sideMin +
                ", sideMid=" + sideMid +
                ", sideMax=" + sideMax +
                "; field=" + getField() +
                '}';
    }
}
