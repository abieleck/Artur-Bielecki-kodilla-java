package com.kodilla.testing.shape;

import java.util.ArrayList;

/*
 * Class ShapeCollector - ordered collection of shapes
 * Duplicate shapes are possible
 * nulls not supported - null argument in any method will result in NullPointerException
 */
public class ShapeCollector {
    ArrayList<Shape> shapeList = new ArrayList<>();

    // add new figure to the end of the collection
    public void addFigure(Shape shape) {
        if (shape == null) {
            throw new NullPointerException();
        }
        shapeList.add(shape);
    }

    // returns true if and only if a shape is removed from collection
    // removes only first occurence of the shape
    public boolean removeFigure(Shape shape) {
        if (shape == null) {
            throw new NullPointerException();
        }
        return shapeList.remove(shape);
    }

    // if n<0 IndexOutOfBounds exception is thrown
    // If n>=(size of collection), null is returned
    public Shape getFigure(int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (n >= shapeList.size()) {
            return null;
        }
        return shapeList.get(n);
    }

    // output information about figures using System.out stream
    // use toString() method for each shape
    // Each shape shown in new line
    public void showFigures() {
        if (shapeList.isEmpty()) {
            System.out.print('\n');
        } else {
            for (Shape shape : shapeList) {
                System.out.print(shape + "\n");
            }
        }
    }

}
