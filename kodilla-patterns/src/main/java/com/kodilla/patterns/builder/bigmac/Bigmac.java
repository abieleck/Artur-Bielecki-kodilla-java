package com.kodilla.patterns.builder.bigmac;

import java.util.*;

public final class Bigmac {

    public enum Sauces {
        STANDARD_SAUCE,
        THOUSAD_ISLANDS_SAUCE,
        BARBECUE_SAUCE
    }

    public enum Rolls {
        SESAME_ROLL,
        NO_SESAME_ROLL
    }

    public enum Ingredients {
        SALAD,
        ONION,
        BECKON,
        CUCUMBER,
        CHILI,
        CHAMPIGNON_MUSHROOMS,
        SHRIMPS,
        CHEESE
    }

    public static class BigmacBuilder {
        private Rolls roll;
        private Integer burgers;
        private Sauces sauce;
        private List<Ingredients> ingredients = new ArrayList<>();

        public BigmacBuilder roll(Rolls roll) {
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

        public BigmacBuilder sauce(Sauces sauce) {
            this.sauce = sauce;
            return this;
        }

        public BigmacBuilder ingredient(Ingredients ingredient) {
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

    private final Rolls roll;
    private final int burgers;
    private final Sauces sauce;
    private final List<Ingredients> ingredients;


    private Bigmac(Rolls roll, int burgers, Sauces sauce, List<Ingredients> ingredients) {
        this.roll = roll;
        this.burgers = burgers;
        this.sauce = sauce;
        this.ingredients = ingredients;
    }

    public Rolls getRoll() {
        return roll;
    }

    public int getBurgers() {
        return burgers;
    }

    public Sauces getSauce() {
        return sauce;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

}
