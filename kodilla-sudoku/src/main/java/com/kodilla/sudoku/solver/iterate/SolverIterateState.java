package com.kodilla.sudoku.solver.iterate;

import com.kodilla.sudoku.board.Board;
import com.kodilla.sudoku.board.SudokuElement;

import java.util.ArrayList;
import java.util.List;

public class SolverIterateState implements Cloneable {

    SolverIterateElement[][] solverBoard = new SolverIterateElement[9][9];
    List<SolverIterateElement> emptyElements = new ArrayList<>();
    private boolean modified;
    private boolean unsolvable;

    public SolverIterateState(Board board) {
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                int value = board.getValue(i+1, j+1);
                SolverIterateElement solverElement = new SolverIterateElement(i, j, value);
                solverBoard[i][j] = solverElement;
                if(value == SudokuElement.EMPTY) {
                    emptyElements.add(solverElement);
                }
            }
        }
    }

    @Override
    public SolverIterateState clone() {
        SolverIterateState clonedState = null;
        try {
            clonedState = (SolverIterateState) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clonedState.solverBoard = new SolverIterateElement[9][9];
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                clonedState.solverBoard[i][j] = solverBoard[i][j].clone();
            }
        }
        clonedState.emptyElements = new ArrayList<>();
        for(SolverIterateElement e: emptyElements) {
            clonedState.emptyElements.add(clonedState.solverBoard[e.getI()][e.getJ()]);
        }
        return clonedState;
    }

    public int getValue(int i, int j) {
        return solverBoard[i][j].getValue();
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public List<SolverIterateElement> getEmptyElements() {
        return new ArrayList<>(emptyElements);
    }

    public void removePossibleValue(int i, int j, int value) {
        SolverIterateElement solverElement = solverBoard[i][j];
        if(solverElement.removePossibleValue(value)) {
            modified = true;
            unsolvable = unsolvable || solverElement.isUnsolvable();
            if(!solverElement.isEmpty()) {
                emptyElements.remove(solverElement);
            }
        }
    }

    public Board getBoard() {
        Board board = new Board();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                board.insert(i+1, j+1, getValue(i, j));
            }
        }
        return board;
    }

    public boolean isModified() {
        return modified;
    }

    public boolean isUnsolvable() {
        return unsolvable;
    }

    public boolean isSolved() {
        return emptyElements.isEmpty() && !unsolvable;
    }

    public void guessValue() {
        SolverIterateElement solverElement = emptyElements.get(emptyElements.size() - 1);
        if(solverElement.guessValue()) {
            modified = true;
            emptyElements.remove(solverElement);
        }
    }

    public void removeGuessedValue() {
        SolverIterateElement solverElement = emptyElements.get(emptyElements.size() - 1);
        solverElement.removeGuessedValue();
        modified = true;
        unsolvable = unsolvable || solverElement.isUnsolvable();
        if(!solverElement.isEmpty()) {
            emptyElements.remove(solverElement);
        }
    }
}
