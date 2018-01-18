package com.kodilla.sudoku.menu;

import java.util.Objects;
import java.util.function.Function;

public class MenuItem {
    private String entry;
    private String pattern;
    private String description;
    private Function<String, AppStatus> action;

    public MenuItem(String entry, String pattern, String description, Function<String, AppStatus> action) {
        this.entry = entry;
        this.pattern = pattern;
        this.description = description;
        this.action = action;
    }

    public void show() {
        System.out.println("'" + entry + "' - " + description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return entry == that.entry;
    }

    @Override
    public int hashCode() {

        return Objects.hash(entry);
    }

    public boolean matches(String userInput) {
        return userInput.matches(pattern);
    }

    public AppStatus execute(String userInput) {
        return action.apply(userInput);
    };
}
