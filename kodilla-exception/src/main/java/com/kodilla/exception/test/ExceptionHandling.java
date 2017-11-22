package com.kodilla.exception.test;

public class ExceptionHandling {

    public static void main(String[] args) {

        SecondChallenge secondChallenge = new SecondChallenge();
        try {
            String result = secondChallenge.probablyIWillThrowException(2.5, 2.0);
            System.out.println("Status of calculations: " + result);
        } catch (Exception e) {
            System.out.println("Status of calculations: exception encountered");
        } finally {
            System.out.println("End of calculations");
        }

    }

}
