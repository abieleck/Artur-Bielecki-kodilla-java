package com.kodilla.sudoku;

import java.util.ArrayList;

public class SudokuBoard {
    ArrayList<SudokuRow> rows = new ArrayList<>();

    public SudokuBoard() {
        for(int i=0; i<9; i++) {
            rows.add(new SudokuRow());
        }
    }

    public void insert(int coordinateX, int coordinateY, int value) {
        rows.get(coordinateY - 1).getSudokuElement(coordinateX).setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("y-------------------\n");
        for(int i=9; i>=1; i--) {
            stringBuilder.append(i + rows.get(i-1).toString() + "\n");
        }
        stringBuilder.append(" -------------------\n");
        stringBuilder.append("x 1 2 3 4 5 6 7 8 9");
        return  stringBuilder.toString();
    }

    public void clear() {
        for(int i=1; i<=9; i++)
            for(int j=1; j<=9; j++) {
                insert(i, j, SudokuElement.EMPTY);
            }
    }
}