package com.kodilla.rps.menu;

import com.kodilla.rps.Input;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {
    private String description;
    private String prompt;
    private Map<Character, MenuItem> menuItems = new LinkedHashMap<>();

    public Menu(String description, String prompt) {
        this.description = description;
        this.prompt = prompt;
    }

    public void addItem(MenuItem item) {
        menuItems.put(item.getKey(), item);
    }

    public AppStatus execute() {
        if(description.length()!=0) {
            System.out.println(description);
        }
        for(MenuItem item : menuItems.values()) {
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
