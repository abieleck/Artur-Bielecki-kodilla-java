package com.kodilla.sudoku;

public class SudokuElement {
    public static final int EMPTY = -1;

    int value = EMPTY;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (value == EMPTY) ? " " : Integer.toString(value);
    }
}
