package com.kodilla.sudoku.solver.iterate;

import com.kodilla.sudoku.NeighbourhoodProcessor;
import com.kodilla.sudoku.board.Board;
import com.kodilla.sudoku.solver.SudokuSolver;

import java.util.*;

public class SolverIterate implements SudokuSolver {

    private Board board;
    private int maxSolutions;
    private int iterations;
    private List<Board> solutions = new ArrayList<>();

    public SolverIterate(Board board, List<String> parameters) {
        this.board = board;
        this.maxSolutions = Integer.parseInt(parameters.get(0));
    }

    private void removeImpossibleValues(SolverIterateState solverState) {
        for (SolverIterateElement e : solverState.getEmptyElements()) {
            int i = e.getI();
            int j = e.getJ();
            NeighbourhoodProcessor neighbourhoodProcessor =
                    new NeighbourhoodProcessor((x, y) -> solverState.getValue(x-1, y-1));
            neighbourhoodProcessor.processNeighbourValues(i+1, j+1,
                    value -> solverState.removePossibleValue(i, j, value));
        }
    }

    @Override
    public void solveSudoku() {
        ArrayDeque<SolverIterateState> stateHeap = new ArrayDeque<>();

        stateHeap.push(new SolverIterateState(board));
        while (!stateHeap.isEmpty() && solutions.size() < maxSolutions) {

            SolverIterateState solverState = stateHeap.peek();
            solverState.setModified(false);
            removeImpossibleValues(solverState);
            iterations++;

            if (solverState.isUnsolvable()) {
                stateHeap.pop();
            } else if (solverState.isSolved()) {
                solutions.add(stateHeap.pop().getBoard());
            } else if (!solverState.isModified()) {
                SolverIterateState clonedState = solverState.clone();
                clonedState.guessValue();
                solverState.removeGuessedValue();
                stateHeap.push(clonedState);
            }
        }
    }

    @Override
    public List<Board> getSolutions() {
        return solutions;
    }

    @Override
    public String getStatistics() {
        return "Average number of iterations per solution (the higher, the more difficult Sudoku): " +
            (double)iterations / (double)solutions.size();
    }

}
