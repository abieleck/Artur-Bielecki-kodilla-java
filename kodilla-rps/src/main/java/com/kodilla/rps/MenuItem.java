package com.kodilla.rps;

public class MenuItem {
    private char key;
    private String description;
    private RunnableWithException action;

    public MenuItem(char key, String description, RunnableWithException action) {
        this.key = key;
        this.description = description;
        this.action = action;
    }

    public void show() {
        System.out.println("'" + key + "' - " + description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        return key == menuItem.key;
    }

    @Override
    public int hashCode() {
        return (int) key;
    }

    public char getKey() {
        return key;
    }

    public void execute() throws EndApplicationException, EndGameException {
        action.run();
    }
}
