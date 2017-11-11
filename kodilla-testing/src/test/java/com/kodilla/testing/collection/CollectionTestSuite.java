package com.kodilla.testing.collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionTestSuite {

    @Test
    public void testOddNumbersExterminatorEmptyList() {
        //Given
        ArrayList<Integer> list = new ArrayList<>();
        OddNumbersExterminator exterminator = new OddNumbersExterminator();
        //When
        ArrayList<Integer> evenList = exterminator.exterminate(list);
        //Then
        Assert.assertTrue(evenList.isEmpty());

    }

    @Test
    public void TestOddNumbersExterminatorNormalList() {
        //Given
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3, 0, 2, 5,-8));
        // ######### dlaczego w powyższym nie muszę wpisywać "new Integer[]{...} w argumencie asList?
        OddNumbersExterminator exterminator = new OddNumbersExterminator();
        //When
        ArrayList<Integer> evenList = exterminator.exterminate(list);
        //Then
        Assert.assertEquals(evenList, new ArrayList<>(Arrays.asList(0, 2,-8)));
    }

    @Before
    public void before() {
        System.out.println("Unit test start");
    }

    @After
    public void after() {
        System.out.println("Unit test end");
    }
}
