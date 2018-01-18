/*
 * This class is a modification of class SudokuDLX written by Rafał Szymański.
 * Original code can be found at:
 * https://github.com/rafalio/dancing-links-java
 * Original code was made available under the MIT licence:
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Rafal Szymanski <rafalio>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.kodilla.sudoku.solver.dancinglinks;

import com.kodilla.sudoku.board.Board;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.solver.SudokuSolver;

import java.util.Arrays;
import java.util.List;

public class SolverDlx implements SudokuSolver {

    private static final int S = 9;
    private static final int side = 3;
    private DancingLinks dlx;
    private Board board;
    private int maxSolutions;

    // sudoku has numbers 1-9. A 0 indicates an empty cell that we will need to
    // fill in.
    private int[][] makeExactCoverGrid(Board board){
        int[][] R = sudokuExactCover();
        for(int i = 1; i <= S; i++){
            for(int j = 1; j <= S; j++){
                int n = board.getValue(i, j);
                if (n == SudokuElement.EMPTY) {
                    n = 0;
                }
                if (n != 0){ // zero out in the constraint board
                    for(int num = 1; num <= S; num++){
                        if (num != n){
                            Arrays.fill(R[getIdx(i, j, num)], 0);
                        }
                    }
                }
            }
        }
        return R;
    }

    // Returns the base exact cover grid for a SUDOKU puzzle
    private int[][] sudokuExactCover(){
        int[][] R = new int[9 * 9 * 9][9 * 9 * 4];

        int hBase = 0;

        // row-column constraints
        for(int r = 1; r <= S; r++){
            for(int c = 1; c <= S; c++, hBase++){
                for(int n = 1; n <= S; n++){
                    R[getIdx(r, c, n)][hBase] = 1;
                }
            }
        }

        // row-number constraints
        for(int r = 1; r <= S; r++){
            for(int n = 1; n <= S; n++, hBase++){
                for(int c1 = 1; c1 <= S; c1++){
                    R[getIdx(r, c1, n)][hBase] = 1;
                }
            }
        }

        // column-number constraints

        for(int c = 1; c <= S; c++){
            for(int n = 1; n <= S; n++, hBase++){
                for(int r1 = 1; r1 <= S; r1++){
                    R[getIdx(r1, c, n)][hBase] = 1;
                }
            }
        }

        // box-number constraints

        for(int br = 1; br <= S; br += side){
            for(int bc = 1; bc <= S; bc += side){
                for(int n = 1; n <= S; n++, hBase++){
                    for(int rDelta = 0; rDelta < side; rDelta++){
                        for(int cDelta = 0; cDelta < side; cDelta++){
                            R[getIdx(br + rDelta, bc + cDelta, n)][hBase] = 1;
                        }
                    }
                }
            }
        }

        return R;
    }

    // row [1,S], col [1,S], num [1,S]
    private int getIdx(int row, int col, int num){
        return (row - 1) * S * S + (col - 1) * S + (num - 1);
    }

    public SolverDlx(Board board, List<String> parameters) {
        this.board = board;
        this.maxSolutions = Integer.parseInt(parameters.get(0));
    }

    protected void runSolver(Board board){
        int[][] cover = makeExactCoverGrid(board);
        dlx = new DancingLinks(cover, maxSolutions);
        dlx.runSolver();
    }

    @Override
    public void solveSudoku() {
        runSolver(board);
    }

    @Override
    public List<Board> getSolutions() {
        return dlx.getSolutions();
    }

    @Override
    public String getStatistics() {
        return dlx.getStatistics();
    }
}
