package com.kodilla.rps;

@FunctionalInterface
public interface RunnableWithException {
    void run() throws EndApplicationException, EndGameException;
}
