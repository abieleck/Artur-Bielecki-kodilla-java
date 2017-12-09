package com.kodilla.good.patterns.challenges.flights.menu;

public class EndApplicationMenu<T> extends Menu<T> {

    public EndApplicationMenu(Menu<T> parentMenu, T exitStatus) {
        super("Are you sure you want to exit the application?", "Enter your choice: ");
        addItem(new MenuItem<T>('y', "exit the application", () -> exitStatus));
        addItem(new MenuItem<T>('n', "return to the list of options", parentMenu::execute));
    }
}
