package com.kodilla.sudoku;

/**
 * <h1>Tools for manipulating indexes of Sudoku board</h1>
 * IndexCalculator class provides static functions that let
 * you cyclically increment indexes of 9x9 Sudoku board.
 * <p>
 *
 * @author  Artur Bielecki
 * @version 1.0
 * @since   2018-01-28
 */
public class IndexCalculator {

    /**
     *  Methods of indexing Sudoku board
     */
    public enum Base {
        /**
         *  Coordinates from 0 to 8
         */
        BASE_0,
        /**
         *  Coordinates from 1 to 9
         */
        BASE_1
    }

    /**
     * This method is used to shift cyclically a coordinate of 9x9 Sudoku board
     *
     * @param index Coordinate of Sudoku board that will be shifted
     * @param shift By how many fields to shift. Negative values are also allowed
     * @param base  Indicates whether Sudoku board is indexed from 0 to 8 or from 1 to 9
     * @return int  Coordinate shifted 'to the right' (if {@code shift > 0}) or 'left' (if {@code shift < 0}).
     * If shifted coordinate exceeds bounds for coordinates it "wraps around" and starts from the beginning (or
     * end, depending on the sign of {@code index}).
     */
    public static int shiftCyclically(int index, int shift, Base base) {
        if (base == Base.BASE_0) {
            return (index + shift) % 9;
        } else {
            return (index - 1 + shift) %9 + 1;
        }
    }

    /**
     * This method is used to shift cyclically a coordinate of 9x9 Sudoku board within one of 9
     * 3x3 blocks
     *
     * @param index Coordinate of Sudoku board that will be shifted.
     * @param shift By how many fields to shift. Negative values are also allowed.
     * @param base  Indicates whether Sudoku board is indexed from 0 to 8 or from 1 to 9.
     * @return int  Coordinate shifted 'to the right' (if {@code shift > 0}) or 'left' (if {@code shift < 0}).
     * If shifted coordinate exceeds bounds for coordinates within the block containing {@code index},
     * it "wraps around" and starts from the beginning (or end, depending on the sign of {@code index})
     * of the block.
     */
    public static int shiftCyclicallyWithinBlock(int index, int shift, Base base) {
        if (base == Base.BASE_0) {
            return index - (index % 3) + (index + shift) % 3;
        } else {
            return index - ((index - 1) % 3) + (index - 1 + shift) % 3;
        }
    }
}
