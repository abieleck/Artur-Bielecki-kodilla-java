package com.kodilla.sudoku.solver;

import com.kodilla.sudoku.board.Board;
import com.kodilla.sudoku.menu.AppStatus;
import com.kodilla.sudoku.menu.MenuItem;
import com.kodilla.sudoku.solver.dancinglinks.SolverDlx;
import com.kodilla.sudoku.solver.dancinglinks.SolverSpecDlx;
import com.kodilla.sudoku.solver.iterate.SolverIterate;
import com.kodilla.sudoku.solver.iterate.SolverSpecIterate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum SolverType {

    ITERATE(new SolverSpecIterate(), SolverIterate::new),
    DLX(new SolverSpecDlx(), SolverDlx::new);

    private SolverSpec solverSpec;
    private BiFunction<Board, List<String>, SudokuSolver> solverGenerator;

    SolverType(SolverSpec solverSpec, BiFunction<Board, List<String>, SudokuSolver> solverGenerator) {
        this.solverSpec = solverSpec;
        this.solverGenerator = solverGenerator;
    }

    private List<String> parseParameters(String input) {
        List<String> result = new ArrayList<>();

        String paramString = input.substring(8 + solverSpec.getName().length());
        int paramStringIndex = 0;
        for(ParameterSpec p: solverSpec.getParameterSpecs()) {
            Pattern pattern = Pattern.compile("^" + p.getRegexpPattern());
            Matcher m = pattern.matcher(paramString.substring(paramStringIndex));
            m.find();
            int paramEnd = m.end();
            result.add(paramString.substring(paramStringIndex, paramStringIndex + paramEnd));
            paramStringIndex += paramEnd + 1;
        }
        return result;
    }

    public MenuItem getMenuItem(Board board) {
        List<ParameterSpec> paramSpecs = solverSpec.getParameterSpecs();
        String entry = paramSpecs.stream()
                .map(p -> p.getName())
                .collect(Collectors.joining(" ", "SUDOKU " + solverSpec.getName() + " ", ""));
        String pattern = paramSpecs.stream()
                .map(p -> p.getRegexpPattern())
                .collect(Collectors.joining(" ", "SUDOKU " + solverSpec.getName() + " ", ""));
        String description = paramSpecs.stream()
                .map(p -> p.getName() + " - " + p.getDescription())
                .collect(Collectors.joining(", ", solverSpec.getDescription() + " ", ""));
        return new MenuItem(entry, pattern, description,
                s -> solveSudokuMenuMethod(board, solverGenerator.apply(board, parseParameters(s))));
    }

    private AppStatus solveSudokuMenuMethod(Board board, SudokuSolver solver) {
        solver.solveSudoku();
        List<Board> solutions = solver.getSolutions();
        if(solutions.isEmpty()) {
            System.out.println("No solutions were found.");
        } else {
            System.out.println("Solutions found:");
            for(Board b: solutions) {
                System.out.println(b);
            }
            System.out.println(solver.getStatistics());
        }
        System.out.println("Your board:");
        System.out.println(board);
        return AppStatus.NORMAL;
    }

}
