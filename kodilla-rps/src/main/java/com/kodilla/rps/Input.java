package com.kodilla.rps;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private static Scanner scanner;

    public static void open() {
        scanner = new Scanner(System.in);
    }

    public static String getInput(String prompt, String pattern) {
        System.out.println(prompt);
        String result;
        boolean resultIsValid;
        do {
            result = scanner.nextLine();
            resultIsValid = result.matches(pattern);
            if (!resultIsValid) {
                System.out.println("Incorrect value, try again: ");
            }
        } while (!result.matches(pattern));
        return result;
    }

    public static void close() {
        scanner.close();
    }


}
