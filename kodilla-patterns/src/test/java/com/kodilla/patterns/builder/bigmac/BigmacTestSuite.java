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
                .ingredient(Bigmac.Ingredients.ONION)
                .ingredient(Bigmac.Ingredients.CUCUMBER)
                .roll(Bigmac.Rolls.SESAME_ROLL)
                .sauce(Bigmac.Sauces.BARBECUE_SAUCE)
                .build();
        //When
        int burgers = bigmac.getBurgers();
        Bigmac.Rolls roll = bigmac.getRoll();
        Bigmac.Sauces sauce = bigmac.getSauce();
        List<Bigmac.Ingredients> ingredients = bigmac.getIngredients();
        //Then
        Assert.assertEquals(3, burgers);
        Assert.assertEquals(Bigmac.Rolls.SESAME_ROLL, roll);
        Assert.assertEquals(Bigmac.Sauces.BARBECUE_SAUCE, sauce);
        Assert.assertEquals(ingredients, Arrays.asList(Bigmac.Ingredients.ONION, Bigmac.Ingredients.CUCUMBER));
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedBurgers() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .ingredient(Bigmac.Ingredients.ONION)
                .roll(Bigmac.Rolls.SESAME_ROLL)
                .sauce(Bigmac.Sauces.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testNegativeBurgers() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(-2)
                .roll(Bigmac.Rolls.SESAME_ROLL)
                .ingredient(Bigmac.Ingredients.ONION)
                .sauce(Bigmac.Sauces.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedRoll() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .ingredient(Bigmac.Ingredients.ONION)
                .sauce(Bigmac.Sauces.BARBECUE_SAUCE)
                .build();
        //Then - exception is thrown
    }

    @Test(expected = IllegalStateException.class)
    public void testUnspecifiedSauce() {
        //Given
        //When
        Bigmac bigmac = new Bigmac.BigmacBuilder()
                .burgers(1)
                .roll(Bigmac.Rolls.SESAME_ROLL)
                .ingredient(Bigmac.Ingredients.ONION)
                .build();
        //Then - exception is thrown
    }

}
