package com.kodilla.stream.array;

import org.junit.Assert;
import org.junit.Test;

public class ArrayOperationsTestSuite {

    @Test
    public void testGetAverage() {
        //Given
        int[] numbers = new int[]{3,5,7,9,10};
        //When
        double result = ArrayOperations.getAverage(numbers);
        //Then
        Assert.assertEquals(6.8, result, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageEmptyArray() {
        //Given
        int[] numbers = new int[0];
        //When
        double result = ArrayOperations.getAverage(numbers);
        //Then - IllegalArgumentException is thrown
    }
}
