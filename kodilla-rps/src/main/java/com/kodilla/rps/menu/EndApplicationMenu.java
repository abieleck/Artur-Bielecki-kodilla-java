package com.kodilla.rps.menu;

public class EndApplicationMenu extends Menu {

    public EndApplicationMenu(Menu parentMenu) {
        super("Are you sure you want to exit the application?", "Enter your choice: ");
        addItem(new MenuItem('y', "exit the application", () -> AppStatus.END_APPLICATION));
        addItem(new MenuItem('n', "return to the list of options", parentMenu::execute));
    }
}
