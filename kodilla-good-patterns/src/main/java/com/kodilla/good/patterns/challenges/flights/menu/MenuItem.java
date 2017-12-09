package com.kodilla.good.patterns.challenges.flights.menu;

import java.util.Objects;
import java.util.function.Supplier;

public class MenuItem<T> {
    private char key;
    private String description;
    private Supplier<T> action;

    public MenuItem(char key, String description, Supplier<T> statusSupplier) {
        this.key = key;
        this.description = description;
        this.action = statusSupplier;
    }

    public void show() {
        System.out.println("'" + key + "' - " + description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem<?> menuItem = (MenuItem<?>) o;
        return key == menuItem.key;
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }

    public char getKey() {
        return key;
    }

    public T execute() {
        return action.get();
    };
}
