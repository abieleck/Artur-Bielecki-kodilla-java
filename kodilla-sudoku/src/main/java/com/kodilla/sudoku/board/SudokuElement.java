package com.kodilla.sudoku.board;

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
