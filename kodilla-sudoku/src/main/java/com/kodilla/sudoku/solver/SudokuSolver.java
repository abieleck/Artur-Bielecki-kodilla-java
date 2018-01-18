package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.board.Board;

import java.util.List;

public interface SudokuSolver {
    void solveSudoku();

    List<Board> getSolutions();

    String getStatistics();

}
