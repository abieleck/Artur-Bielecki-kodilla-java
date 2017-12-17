package com.kodilla.patterns.builder.bigmac;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BigmacTestSuite {

    @Test
    public void testBigmacBuilder() {
        //Given
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(3)
                .ingredient("Onion")
                .ingredient("Cucumber")
                .roll(Bigmac.SESAME_ROLL)
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //When
        int burgers = bigmac.getBurgers();
        String roll = bigmac.getRoll();
        String sauce = bigmac.getSauce();
        List<String> ingredients = bigmac.getIngredients();
        //Then
        Assert.assertEquals(3, burgers);
        Assert.assertEquals(Bigmac.SESAME_ROLL, roll);
        Assert.assertEquals(Bigmac.BARBECUE_SAUCE, sauce);
        Assert.assertEquals(ingredients, Arrays.asList("Onion", "Cucumber"));
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedBurgers() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .ingredient("Onion")
                .roll(Bigmac.SESAME_ROLL)
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testNegativeBurgers() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(-2)
                .roll(Bigmac.SESAME_ROLL)
                .ingredient("Onion")
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedRoll() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .ingredient("Onion")
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testIncorrectRoll() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .roll("incorrect roll")
                .ingredient("Onion")
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedSauce() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .roll(Bigmac.SESAME_ROLL)
                .ingredient("Onion")
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testIncorrectSauce() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .roll(Bigmac.SESAME_ROLL)
                .ingredient("Onion")
                .sauce("incorrect sauce")
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testIncorrectIngredient() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .roll(Bigmac.SESAME_ROLL)
                .ingredient("incorrect ingredient")
                .sauce(Bigmac.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }


}
