package com.kodilla.sudoku.solver.iterate;

import com.kodilla.sudoku.solver.ParameterSpec;
import com.kodilla.sudoku.solver.SolverSpec;

import java.util.Arrays;
import java.util.List;

public class SolverSpecIterate implements SolverSpec {

    private static List<ParameterSpec> parameterSpecs = Arrays.asList(
            new ParameterSpec(
                    "<max solutions>",
                    "(100|[1-9][0-9]?)",
                    "maximum number of solutions (1-100)"
            )
    );

    @Override
    public String getName() {
        return "ITERATE";
    }

    @Override
    public String getDescription() {
        return "solve with brute force iterative approach.";
    }

    @Override
    public List<ParameterSpec> getParameterSpecs() {
        return parameterSpecs;
    }
}
