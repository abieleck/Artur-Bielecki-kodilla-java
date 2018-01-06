package com.kodilla.sudoku;

import com.kodilla.sudoku.menu.AppStatus;
import com.kodilla.sudoku.menu.EndApplicationMenu;
import com.kodilla.sudoku.menu.Menu;
import com.kodilla.sudoku.menu.MenuItem;

import java.util.List;

public class SudokuRunner {

    public static void main(String[] args) {
        SudokuRunner sudokuRunner = new SudokuRunner();
        sudokuRunner.run();
    }

    Menu menu = new Menu("Select action:", "Enter your choice:");
    EndApplicationMenu endApplicationMenu = new EndApplicationMenu(menu);
    SudokuBoard board = new SudokuBoard();

    private void menuSetup() {
        menu.addItem(new MenuItem("x,y,n...", "^[1-9](,[1-9]){2}((,[1-9]){3})*$",
                "insert number n (1-9) into board at coordinates x (1-9) and y (1-9). " +
                        "Multiple triples are allowed, e.g. '2,4,1,3,5,2' enters 1 at (2,4) and 2 at (3,5)",
                s -> insertToBoard(s)));
        menu.addItem(new MenuItem("c", "^c$","clear board", s -> clearBoard()));
        menu.addItem(new MenuItem("SUDOKU", "^SUDOKU$", "solve sudoku (at most 10 solutions)",
                s -> solveSudoku(10)));
        menu.addItem(new MenuItem("SUDOKU n", "^SUDOKU ([1-9][0-9]?|100)$", "solve sudoku " +
                "(at most n solutions, n between 1 and 100)",
                s -> solveSudoku(Integer.parseInt(s.substring(7)))));
        menu.addItem(new MenuItem("x","^x$", "exit application",
                s -> endApplicationMenu.execute()));
    }

    private void run() {
        Input.open();
        menuSetup();
        System.out.println("Welcome to Sudoku solver! Start with empty board:");
        System.out.println(board);
        AppStatus applicationStatus;
        do {
            applicationStatus = menu.execute();
        } while (applicationStatus != AppStatus.END_APPLICATION);
        Input.close();
    }

    private AppStatus insertToBoard(String valuesAtCoordinates) {
        String[] values = valuesAtCoordinates.split(",");
        for(int i=0; i < values.length; i+=3) {
            board.insert(Integer.parseInt(values[i]), Integer.parseInt(values[i+1]), Integer.parseInt(values[i+2]));
        }
        System.out.println("Your board:");
        System.out.println(board);
        return AppStatus.NORMAL;
    }

    private AppStatus solveSudoku(int maxSolutions) {
        SudokuSolverFactory sudokuSolverFactory = new SudokuSolverFactory();
        SudokuSolver sudokuSolver = sudokuSolverFactory.getSudokuSolver(board, maxSolutions,
                SudokuSolverFactory.Algorithms.ITERATE_THROUGH_BOARD);
        sudokuSolver.solveSudoku();
        List<SudokuBoard> solutions = sudokuSolver.getSolutions();
        if(solutions.isEmpty()) {
            System.out.println("This Sudoku does not have solutions");
        } else {
            System.out.println("Solutions found:");
            for(SudokuBoard b: solutions) {
                System.out.println(b);
            }
            System.out.print("Average number of iterations per solution (the higher, the more difficult Sudoku): ");
            System.out.println((double)sudokuSolver.getIterations() / (double)solutions.size());
        }
        System.out.println("Your board:");
        System.out.println(board);
        return AppStatus.NORMAL;
    }

    private AppStatus clearBoard() {
        board.clear();
        System.out.println("Your board:");
        System.out.println(board);
        return AppStatus.NORMAL;
    }
}
