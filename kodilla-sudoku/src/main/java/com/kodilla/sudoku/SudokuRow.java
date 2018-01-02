package com.kodilla.sudoku;

import java.util.ArrayList;

public class SudokuRow {
    ArrayList<SudokuElement> elements = new ArrayList<>();

    public SudokuRow() {
        for(int i=0; i<9; i++) {
            elements.add(new SudokuElement());
        }
    }

    public SudokuElement getSudokuElement(int coordinateX) {
        return elements.get(coordinateX - 1);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|");
        for(SudokuElement e: elements) {
            stringBuilder.append(e.toString());
            stringBuilder.append("|");
        }
        return  stringBuilder.toString();
    }
}
