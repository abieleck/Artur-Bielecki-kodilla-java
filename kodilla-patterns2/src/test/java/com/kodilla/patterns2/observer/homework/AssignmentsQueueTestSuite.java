package com.kodilla.patterns2.observer.homework;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssignmentsQueueTestSuite {

    @Test
    public void testAssignmentQueues() {
        //Given
        AssignmentsQueue johnSmithQueue = new AssignmentsQueue("John Smith");
        AssignmentsQueue kathrinSmithQueue = new AssignmentsQueue("Kathrin Smith");
        AssignmentsQueue adamSmithQueue = new AssignmentsQueue("Adam Smith");

        Mentor mentor1 = new Mentor("Mentor1");
        Mentor mentor2 = new Mentor("Mentor2");

        johnSmithQueue.registerObserver(mentor1);
        kathrinSmithQueue.registerObserver(mentor1);
        adamSmithQueue.registerObserver(mentor1);

        johnSmithQueue.registerObserver(mentor2);
        kathrinSmithQueue.registerObserver(mentor2);

        //When
        johnSmithQueue.addAssignment(new Assignment("Assignment 1"));
        johnSmithQueue.addAssignment(new Assignment("Assignment 2"));

        johnSmithQueue.removeObserver(mentor1);
        johnSmithQueue.addAssignment(new Assignment("Assignment 3"));

        kathrinSmithQueue.addAssignment(new Assignment("Assignment 4"));
        kathrinSmithQueue.addAssignment(new Assignment("Assignment 5"));

        adamSmithQueue.addAssignment(new Assignment("Assignment 6"));

        johnSmithQueue.removeAssignment();
        kathrinSmithQueue.removeAssignment();
        //Then
        assertEquals(6, mentor1.getUpdateCount());
        assertEquals(7, mentor2.getUpdateCount());

    }
}
