package com.kodilla.sudoku;

public class SudokuSolverFactory {
    public SudokuSolver getSudokuSolver(Board board, int maxSolutions, Solvers solver) {
        return new SudokuSolverIterate(board, maxSolutions);
    }
}
