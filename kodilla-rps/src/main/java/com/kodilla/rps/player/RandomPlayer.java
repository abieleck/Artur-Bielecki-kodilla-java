package com.kodilla.rps.player;

import com.kodilla.rps.settings.Settings;

import java.util.Random;

public class RandomPlayer implements ComputerPlayer {
    Random randomGenerator = new Random();
    int movesCount;

    public RandomPlayer(Settings settings) {
        this.movesCount = settings.getMovesCount();
    }

    @Override
    public int nextMove(int opponentMove) {
        return randomGenerator.nextInt(movesCount) + 1;
    }
}
