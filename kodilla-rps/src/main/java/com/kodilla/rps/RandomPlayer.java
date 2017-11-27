package com.kodilla.rps;

import java.util.Random;

public class RandomPlayer implements ComputerPlayer {
    Random randomGenerator = new Random();
    int movesCount;

    public RandomPlayer(int movesCount) {
        this.movesCount = movesCount;
    }

    @Override
    public char nextMove(char opponentMove) {
        return Integer.toString(randomGenerator.nextInt(movesCount) + 1).charAt(0);
    }
}
