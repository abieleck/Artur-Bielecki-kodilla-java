package com.kodilla.sudoku.menu;

public class EndApplicationMenu extends Menu {

    public EndApplicationMenu(Menu parentMenu) {
        super("Are you sure you want to exit the application?", "Enter your choice: ");
        addItem(new MenuItem("y", "^y$", "exit the application", s -> AppStatus.END_APPLICATION));
        addItem(new MenuItem("n", "^n$", "return to the list of options",
                s -> parentMenu.execute()));
    }
}
