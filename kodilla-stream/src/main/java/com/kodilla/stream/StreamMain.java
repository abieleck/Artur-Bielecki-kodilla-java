package com.kodilla.stream;

import com.kodilla.stream.beautifier.PoemBeautifier;
import com.kodilla.stream.lambda.ExpressionExecutor;

import com.kodilla.stream.reference.FunctionalCalculator;

public class StreamMain {

    public static String addUderscoresBetween(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<text.length(); i++) {
            stringBuilder.append(text.charAt(i));
            stringBuilder.append('_');
        }
        if(text.length()>0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();

        System.out.println("Calculating expressions with lambdas");
        expressionExecutor.executeExpression(10, 5, (a, b) -> a + b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a - b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a * b);
        expressionExecutor.executeExpression(10, 5, (a, b) -> a / b);

        System.out.println("Calculating expressions with method references");
        expressionExecutor.executeExpression(3, 4, FunctionalCalculator::multiplyAByB);
        expressionExecutor.executeExpression(3, 4, FunctionalCalculator::addAToB);
        expressionExecutor.executeExpression(3, 4, FunctionalCalculator::subBFromA);
        expressionExecutor.executeExpression(3, 4, FunctionalCalculator::divideAByB);

        System.out.println("Beautifying text");
        PoemBeautifier poemBeautifier = new PoemBeautifier();
        poemBeautifier.beautify("Text to beautify", s -> "__" + s + "__");
        poemBeautifier.beautify("Text to beautify", s -> s.toUpperCase());
        poemBeautifier.beautify("Text to beautify", StreamMain::addUderscoresBetween);
        poemBeautifier.beautify("Text to beautify", s -> s.replace(' ', '_'));
        poemBeautifier.beautify("Text to beautify", s -> (new StringBuilder(s)).reverse().toString());
    }
}
