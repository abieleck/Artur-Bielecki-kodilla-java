package com.kodilla.stream.world;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class WorldTestSuite {

    @Test
    public void testGetPeopleQuantity() {
        //Given
        Country poland = new Country("Poland", new BigDecimal("39000000"));
        Country germany = new Country("Germany", new BigDecimal("70000000"));
        Country france = new Country("France", new BigDecimal("80000000"));
        Continent europe = new Continent("Europe", poland, germany, france);

        Country china = new Country("China", new BigDecimal("1200000000"));
        Country india = new Country("India", new BigDecimal("1100000000"));
        Continent asia = new Continent("Asia", china, india);

        Country usa = new Country("USA", new BigDecimal("300000000"));
        Country canada = new Country("Canada", new BigDecimal("25000000"));
        Continent northAmerica = new Continent("North America", usa, canada);

        World world = new World("Earth", europe, asia, northAmerica);
        //When
        BigDecimal result = world.getPeopleQuantity();
        //Then
        Assert.assertEquals(new BigDecimal("2814000000"), result);
    }
}
