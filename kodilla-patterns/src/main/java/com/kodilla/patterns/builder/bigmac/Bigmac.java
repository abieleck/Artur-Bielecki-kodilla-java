package com.kodilla.patterns.builder.bigmac;

import java.util.*;

public final class Bigmac {

    private final String roll;
    private final int burgers;
    private final String sauce;
    private final List<String> ingredients;

    public final static String SESAME_ROLL = "Sesame roll";
    public final static String NO_SESAME_ROLL = "No sesame roll";
    public final static String STANDARD_SAUCE = "Standard sauce";
    public final static String THOUSAD_ISLANDS_SAUCE = "1000 islands sauce";
    public final static String BARBECUE_SAUCE = "BBQ sauce";
    public final static Set<String> INGREDIENTS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("Salad", "Onion", "Beckon", "Cucumber", "Chilli",
                    "Champignon mushroom", "Shrimps", "Cheese")));


    private Bigmac(String roll, int burgers, String sauce, List<String> ingredients) {
        this.roll = roll;
        this.burgers = burgers;
        this.sauce = sauce;
        this.ingredients = ingredients;
    }

    public String getRoll() {
        return roll;
    }

    public int getBurgers() {
        return burgers;
    }

    public String getSauce() {
        return sauce;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public static class BigmacBuilder {
        private String roll;
        private Integer burgers;
        private String sauce;
        private List<String> ingredients = new ArrayList<>();

        public BigmacBuilder roll(String roll) {
            if (!roll.equals(SESAME_ROLL) && !roll.equals(NO_SESAME_ROLL)) {
                throw new IllegalStateException("Incorrect name of roll");
            }
            this.roll = roll;
            return this;
        }

        public BigmacBuilder burgers(int burgers) {
            if (burgers < 0) {
                throw new IllegalStateException("Negative number of burgers");
            }
            this.burgers = burgers;
            return this;
        }

        public BigmacBuilder sauce(String sauce) {
            if (!sauce.equals(STANDARD_SAUCE) &&
                    !sauce.equals(THOUSAD_ISLANDS_SAUCE) &&
                    !sauce.equals(BARBECUE_SAUCE)) {
                throw new IllegalStateException("Incorrect name of sauce");
            }
            this.sauce = sauce;
            return this;
        }

        public BigmacBuilder ingredient(String ingredient) {
            if (!INGREDIENTS.contains(ingredient)) {
                throw new IllegalStateException("Incorrect name of ingredient : " + ingredient);
            }
            ingredients.add(ingredient);
            return this;
        }

        public Bigmac build() {
            if (roll == null) {
                throw new IllegalStateException("Roll not specified");
            } else if (sauce == null) {
                throw new IllegalStateException("Sauce not specified");
            } else if (burgers == null) {
                throw new IllegalStateException("Number of burgers not specified");
            } else {
                return new Bigmac(roll, burgers, sauce, ingredients);
            }

        }
    }
}
