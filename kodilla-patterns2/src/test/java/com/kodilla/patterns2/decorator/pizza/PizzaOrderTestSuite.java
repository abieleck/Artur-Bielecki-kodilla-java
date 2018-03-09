package com.kodilla.patterns2.decorator.pizza;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PizzaOrderTestSuite {

    @Test
    public void testBasicPizzaOrderGetPrice() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        //When
        BigDecimal price = theOrder.getPrice();
        //Then
        assertEquals(price, new BigDecimal("15"));
    }

    @Test
    public void testBasicPizzaOrderGetDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        //When
        String description = theOrder.getDescription();
        //Then
        assertEquals(description, "Pizza with tomato sauce, cheese");
    }

    @Test
    public void testMushroomsRucolaPizzaOrderGetPrice() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        theOrder = new RucolaPizzaOrderDecorator(theOrder);
        //When
        BigDecimal price = theOrder.getPrice();
        //Then
        assertEquals(price, new BigDecimal("20"));
    }

    @Test
    public void testMushroomsRucolaPizzaOrderGetDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        theOrder = new RucolaPizzaOrderDecorator(theOrder);
        //When
        String description = theOrder.getDescription();
        //Then
        assertEquals(description, "Pizza with tomato sauce, cheese, mushrooms, rucola");
    }

    @Test
    public void test2MushroomsPizzaOrderGetPrice() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        //When
        BigDecimal price = theOrder.getPrice();
        //Then
        assertEquals(price, new BigDecimal("21"));
    }

    @Test
    public void test2MushroomsPizzaOrderGetDescription() {
        //Given
        PizzaOrder theOrder = new BasicPizzaOrder();
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        theOrder = new MushroomsPizzaOrderDecorator(theOrder);
        //When
        String description = theOrder.getDescription();
        //Then
        assertEquals(description, "Pizza with tomato sauce, cheese, mushrooms, mushrooms");
    }
}
