package com.kodilla.patterns.factory.tasks;

public class TaskFactory {
    public static final String DRIVING = "DRIVING";
    public static final String PAINTING = "PAINTING";
    public static final String SHOPPING = "SHOPPING";

    public Task makeTask(final String taskClass) {
        switch (taskClass) {
            case DRIVING:
                return new DrivingTask("Driving", "Warsaw", "car");
            case PAINTING:
                return new PaintingTask("Painting", "red", "wall");
            case SHOPPING:
                return new ShoppingTask("Shopping", "bread", 2);
            default:
                return null;
        }
    }
}
