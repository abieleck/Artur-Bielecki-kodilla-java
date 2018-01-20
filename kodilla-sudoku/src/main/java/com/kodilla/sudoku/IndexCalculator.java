package com.kodilla.sudoku;

public class IndexCalculator {

    public enum Base {
        BASE_0, // means board elements are indexed using numbers 0-8
        BASE_1;  // board elements indexed using numbers 1-9
    }

    // shift index "to the right" and at the end "wrap around" and start from the beginning (from "the left")
    public static int shiftCyclically(int index, int shift, Base base) {
        if (base == Base.BASE_0) {
            return (index + shift) % 9;
        } else {
            return (index - 1 + shift) %9 + 1;
        }
    }

    // works like "ShiftCyclically" but within block of lenght 3 containing the index
    // for example, if zero-based index = 5, it will be shifted within 3-5
    public static int shiftCyclicallyWithinBlock(int index, int shift, Base base) {
        if (base == Base.BASE_0) {
            return index - (index % 3) + (index + shift) % 3;
        } else {
            return index - ((index - 1) % 3) + (index - 1 + shift) % 3;
        }
    }
}
