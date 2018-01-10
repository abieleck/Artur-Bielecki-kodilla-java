package com.kodilla.sudoku.solver.iterate;

import com.kodilla.sudoku.SudokuElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SolverIterateElement implements Cloneable{
    private final int i;
    private final int j;
    private List<Integer> possibleValues;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean removePossibleValue(int value) {
        return possibleValues.remove(new Integer(value));
    }

    public void removeGuessedValue() {
        possibleValues.remove(possibleValues.size() - 1);
    }

    public boolean guessValue() {
        boolean result = possibleValues.size() > 1;
        possibleValues = Arrays.asList(possibleValues.get(possibleValues.size() - 1));
        return result;
    }

    public boolean isUnsolvable() {
        return possibleValues.isEmpty();
    }

    public boolean isEmpty() {
        return possibleValues.size() != 1;
    }

    public SolverIterateElement(int i, int j, int value) {
        this.i = i;
        this.j = j;
        if(value == SudokuElement.EMPTY) {
            possibleValues = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9));
        } else {
            possibleValues = new ArrayList<>();
            possibleValues.add(value);
        }
    }

    public int getValue() {
        if (isEmpty()) {
            return SudokuElement.EMPTY;
        }
        return possibleValues.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolverIterateElement that = (SolverIterateElement) o;
        return i == that.i &&
                j == that.j;
    }


    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public SolverIterateElement clone() {
        SolverIterateElement clonedElement = null;
        try {
            clonedElement = (SolverIterateElement) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clonedElement.possibleValues = new ArrayList<>(possibleValues);
        return clonedElement;
    }

}
