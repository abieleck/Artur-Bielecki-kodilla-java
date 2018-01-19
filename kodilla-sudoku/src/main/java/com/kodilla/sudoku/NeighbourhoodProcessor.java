package com.kodilla.sudoku;

import com.kodilla.sudoku.board.SudokuElement;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class NeighbourhoodProcessor {
    private BiFunction<Integer, Integer, Integer> valueProvider;

    /*
     * Verify if the value placed at coordinates x,y does not
     * cause repetition in row, column or block
     */
    public boolean valueValidAtCoordinates(int x, int y, int value) {
        // For empty elements skip checking
        if(value == SudokuElement.EMPTY) {
            return true;
        }
        boolean noRepetition = true;
        // check row and column, skip coordinates x,y
        for(int i=1; i<9 && noRepetition; i++) {
            // (x - 1 + i) % 9 + 1 goes through all numbers from 1 to 9 except x
            noRepetition = valueProvider.apply((x - 1 + i) % 9 + 1, y) != value // check row, skip coordinate x
                && valueProvider.apply(x, (y - 1 + i) % 9 + 1) != value;        // check column, skip coordinate y
        }
        // check block, skip values in the same row or column because they have been checked above
        for(int i = 1; i < 3 && noRepetition; i++) {
            for(int j = 1; j < 3 && noRepetition; j++) {
                // x - (x-1) % 3 + ((x-1) % 3 + i) % 3 goes through all numbers in the same block except x
                // e.g. for x=5 it goes through 4-6, omitting 5
                noRepetition = valueProvider.apply(x - (x-1) % 3 + ((x-1) % 3 + i) % 3,
                        y - (y-1) % 3 + ((y-1) % 3 + j) % 3) != value;
            }
        }

        return noRepetition;
    }

    // processes values in the same row column and block, excluding board field x,y
    public void processNeighbourValues(int x, int y, Consumer<Integer> valueProcessor) {
        // process row and column, skip coordinates x,y
        for(int i=1; i<9; i++) {
            // (x - 1 + i) % 9 + 1 goes through all numbers from 1 to 9 except x
            valueProcessor.accept(valueProvider.apply((x - 1 + i) % 9 + 1, y)); // go through row skip coordinate x
            valueProcessor.accept(valueProvider.apply(x, (y - 1 + i) % 9 + 1)); // go through column, skip y
        }
        // process block, skip values in the same row or column because they have been checked above
        for(int i = 1; i < 3; i++) {
            for(int j = 1; j < 3; j++) {
                // x - (x-1) % 3 + ((x-1) % 3 + i) % 3 goes through all numbers in the same block except x
                // e.g. for x=5 it goes through 4-6, omitting 5
                valueProcessor.accept(valueProvider.apply(x - (x-1) % 3 + ((x-1) % 3 + i) % 3,
                        y - (y-1) % 3 + ((y-1) % 3 + j) % 3));
            }
        }
    }

    public NeighbourhoodProcessor(BiFunction<Integer, Integer, Integer> valueProvider) {
        this.valueProvider = valueProvider;
    }
}
