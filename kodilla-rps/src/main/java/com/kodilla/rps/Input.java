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
        while(true) {
            try {
                return scanner.next(pattern);
            } catch (InputMismatchException e) {
                System.out.println("Incorrect value, try again: ");
                scanner.next(); // if input not matched, it remains unread and needs to be read
            }
        }
    }

    public static void close() {
        scanner.close();
    }


}
