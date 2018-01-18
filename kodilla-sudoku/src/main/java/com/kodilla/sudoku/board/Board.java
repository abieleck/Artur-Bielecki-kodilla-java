package com.kodilla.sudoku.board;

import java.util.ArrayList;

public class Board {
    ArrayList<SudokuRow> rows = new ArrayList<>();

    public Board() {
        for(int i=0; i<9; i++) {
            rows.add(new SudokuRow());
        }
    }

    public void insert(int coordinateX, int coordinateY, int value) {
        rows.get(coordinateY - 1).getSudokuElement(coordinateX).setValue(value);
    }

    public int getValue(int coordinateX, int coordinateY) {
        return rows.get(coordinateY - 1).getSudokuElement(coordinateX).getValue();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("y -------------------\n");
        for(int i=9; i>=1; i--) {
            stringBuilder
                    .append(i)
                    .append(" ")
                    .append(rows.get(i-1).toString())
                    .append("\n");
        }
        stringBuilder
                .append("  -------------------\n")
                .append("x  1 2 3 4 5 6 7 8 9");
        return  stringBuilder.toString();
    }

    public void clear() {
        for(int i=1; i<=9; i++)
            for(int j=1; j<=9; j++) {
                insert(i, j, SudokuElement.EMPTY);
            }
    }
}
