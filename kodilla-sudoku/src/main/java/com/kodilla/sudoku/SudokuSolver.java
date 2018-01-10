package com.kodilla.sudoku;

import java.util.List;

public interface SudokuSolver {
    void solveSudoku();

    List<Board> getSolutions();

    int getIterations();
}
