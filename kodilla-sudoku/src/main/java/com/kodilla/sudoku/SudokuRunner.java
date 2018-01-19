package com.kodilla.sudoku;

import com.kodilla.sudoku.board.Board;
import com.kodilla.sudoku.board.SudokuElement;
import com.kodilla.sudoku.menu.*;
import com.kodilla.sudoku.solver.SolverType;

public class SudokuRunner {

    public static void main(String[] args) {
        SudokuRunner sudokuRunner = new SudokuRunner();
        sudokuRunner.run();
    }

    Menu menu = new Menu("Select action:", "Enter your choice:");
    EndApplicationMenu endApplicationMenu = new EndApplicationMenu(menu);
    Board board = new Board();
    NeighbourhoodProcessor neighbourhoodProcessor = new NeighbourhoodProcessor((x, y) -> board.getValue(x, y));

    private void menuSetup() {
        menu.addItem(new MenuItem("x,y,n...", "^[1-9],[1-9],[0-9](,[1-9],[1-9],[0-9])*$",
                "insert number n (0-9) into board at coordinates x (1-9) and y (1-9). " +
                        "Multiple triples are allowed, e.g. '2,4,1,3,5,2' enters 1 at (2,4) and 2 at (3,5). Value 0 " +
                        "means \"empty\"",
                s -> insertToBoard(s)));
        menu.addItem(new MenuItem("c", "^c$","clear board", s -> clearBoard()));
        for(SolverType t: SolverType.values()) {
            menu.addItem(t.getMenuItem(board));
        }
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
        boolean validValue = true;
        int x = 0, y = 0, value = 0;
        for(int i=0; i < values.length && validValue; i+=3) {
            x = Integer.parseInt(values[i]);
            y = Integer.parseInt(values[i+1]);
            value = Integer.parseInt(values[i+2]);
            if(value == 0) {
                value = SudokuElement.EMPTY;
            }
            validValue = neighbourhoodProcessor.valueValidAtCoordinates(x, y, value);
            if(validValue) {
                board.insert(x, y, value);
            }
        }
        if(!validValue) {
            System.out.println("WARNING: value " + value + " is not allowed at coordinates x=" + x + " y=" + y +
                    " (repeats in row, column or 3x3 block). Subsequent values (if any) have been ignored." );
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
