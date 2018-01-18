package com.kodilla.sudoku.solver;

public class ParameterSpec {

    private String name;
    private String RegexpPattern;
    private String description;

    public ParameterSpec(String name, String regexpPattern, String description) {
        this.name = name;
        RegexpPattern = regexpPattern;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getRegexpPattern() {
        return RegexpPattern;
    }

    public String getDescription() {
        return description;
    }
}
