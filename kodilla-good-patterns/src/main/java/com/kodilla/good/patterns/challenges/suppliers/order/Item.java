package com.kodilla.good.patterns.challenges.suppliers.order;

public final class Item {

    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
