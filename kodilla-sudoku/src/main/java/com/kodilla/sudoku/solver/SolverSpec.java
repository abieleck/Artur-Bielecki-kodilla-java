package com.kodilla.sudoku.solver;

import java.util.List;

public interface SolverSpec {

    String getName();

    String getDescription();

    List<ParameterSpec> getParameterSpecs();
}
