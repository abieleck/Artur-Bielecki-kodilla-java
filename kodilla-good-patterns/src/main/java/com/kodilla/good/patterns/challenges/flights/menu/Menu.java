package com.kodilla.good.patterns.challenges.flights.menu;

import com.kodilla.good.patterns.challenges.flights.Input;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu<T> {
    private String description;
    private String prompt;
    private Map<Character, MenuItem<T>> menuItems = new LinkedHashMap<>();

    public Menu(String description, String prompt) {
        this.description = description;
        this.prompt = prompt;
    }

    public void addItem(MenuItem<T> item) {
        menuItems.put(item.getKey(), item);
    }

    public T execute() {
        if(description.length()!=0) {
            System.out.println(description);
        }
        for(MenuItem<T> item : menuItems.values()) {
            item.show();
        }
        String pattern = "[" + menuItems.keySet().stream()
                .map(s -> Character.toString(s))
                .collect(Collectors.joining())
                + "]";
        char selection = Input.getInput(prompt, pattern).charAt(0);
        return menuItems.get(selection).execute();
    }
}
