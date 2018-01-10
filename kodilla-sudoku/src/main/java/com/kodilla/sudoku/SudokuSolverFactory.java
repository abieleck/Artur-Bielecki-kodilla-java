package com.kodilla.sudoku;

import com.kodilla.sudoku.solver.iterate.SudokuSolverIterate;

public class SudokuSolverFactory {

    public enum Solvers {
        ITERATE_THROUGH_BOARD
    }

    public SudokuSolver getSudokuSolver(Board board, int maxSolutions, Solvers solver) {

        switch (solver) {
            case ITERATE_THROUGH_BOARD:
                return new SudokuSolverIterate(board, maxSolutions);
            default:
                return null;
        }
    }
}
