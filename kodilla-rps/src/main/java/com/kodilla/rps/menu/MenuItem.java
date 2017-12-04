package com.kodilla.rps.menu;

import java.util.Objects;
import java.util.function.Supplier;

public class MenuItem {
    private char key;
    private String description;
    private Supplier<AppStatus> action;

    public MenuItem(char key, String description, Supplier<AppStatus> statusSupplier) {
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
        MenuItem that = (MenuItem) o;
        return key == that.key;
    }

    @Override
    public int hashCode() {

        return Objects.hash(key);
    }

    public char getKey() {
        return key;
    }

    public AppStatus execute() {
        return action.get();
    };
}
