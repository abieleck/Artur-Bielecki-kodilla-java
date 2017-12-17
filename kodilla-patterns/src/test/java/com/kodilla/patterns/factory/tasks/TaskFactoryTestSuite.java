package com.kodilla.patterns.factory.tasks;

import org.junit.Assert;
import org.junit.Test;

public class TaskFactoryTestSuite {

    private static TaskFactory taskFactory = new TaskFactory();

    @Test
    public void testMakeDrivingTask() {
        //Given
        //When
        Task task = taskFactory.makeTask(TaskFactory.DRIVING);
        //Then
        Assert.assertEquals("Driving", task.getTaskName());
    }

    @Test
    public void testMakePaintingTask() {
        //Given
        //When
        Task task = taskFactory.makeTask(TaskFactory.PAINTING);
        //Then
        Assert.assertEquals("Painting", task.getTaskName());
    }

    @Test
    public void testMakeShoppingTask() {
        //Given
        //When
        Task task = taskFactory.makeTask(TaskFactory.SHOPPING);
        //Then
        Assert.assertEquals("Shopping", task.getTaskName());
    }
}
