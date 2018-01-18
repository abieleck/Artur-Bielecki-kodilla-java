package com.kodilla.sudoku.solver.dancinglinks;

import com.kodilla.sudoku.solver.ParameterSpec;
import com.kodilla.sudoku.solver.SolverSpec;

import java.util.Arrays;
import java.util.List;

public class SolverSpecDlx implements SolverSpec {

    private static List<ParameterSpec> parameterSpecs = Arrays.asList(
        new ParameterSpec(
                "<max solutions>",
                "(100|[1-9][0-9]?)",
                "maximum number of solutions (1-100)"
        )
    );

    @Override
    public String getName() {
        return "DANCING_LINKS";
    }

    @Override
    public String getDescription() {
        return "solve Sudoku with dancing links algorithm.";
    }

    @Override
    public List<ParameterSpec> getParameterSpecs() {
        return parameterSpecs;
    }
}
