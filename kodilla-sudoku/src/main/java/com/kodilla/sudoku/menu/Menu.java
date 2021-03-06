package com.kodilla.sudoku.menu;

import com.kodilla.sudoku.Input;

import java.util.*;

public class Menu {
    private String description;
    private String prompt;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(String description, String prompt) {
        this.description = description;
        this.prompt = prompt;
    }

    public void addItem(MenuItem item) {
        menuItems.add(item);
    }

    public AppStatus execute() {
        if(description.length()!=0) {
            System.out.println(description);
        }
        for(MenuItem item : menuItems) {
            item.show();
        }
        Optional<MenuItem> matchingItem;
        String userInput;
        System.out.print(prompt);
        do {
            String input = Input.getInput("", "^.*$");
            matchingItem = menuItems.stream()
                    .filter(menuItem -> menuItem.matches(input))
                    .findFirst();
            if (!matchingItem.isPresent()) {
                System.out.print("Incorrect value, try again:");
            }
            userInput = input;
        } while (!matchingItem.isPresent());

        return matchingItem.get().execute(userInput);

    }

}
