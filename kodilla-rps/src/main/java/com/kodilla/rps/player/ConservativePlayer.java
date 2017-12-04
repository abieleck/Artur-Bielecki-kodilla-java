package com.kodilla.rps.player;

import com.kodilla.rps.settings.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ConservativePlayer implements ComputerPlayer {
    private List<Integer> listOfBestMoves = new ArrayList<>();
    private Random randomGenerator = new Random();

    public ConservativePlayer(Settings settings) {
        int movesCount = settings.getMovesCount();
        int minScore = IntStream.rangeClosed(1, movesCount-1)
                .flatMap(i -> IntStream.rangeClosed(i+1, movesCount)
                        .map(j -> settings.getScore(i, j)))
                .map(Math::abs)
                .min().getAsInt();
        for(int i=1; i<movesCount; i++)
            for(int j=i+1; j<=movesCount; j++) {
                int score = settings.getScore(i,j);
                if (Math.abs(settings.getScore(i,j)) == minScore) {
                    listOfBestMoves.add((score<0) ? i : j);
                }
            }
    }

    @Override
    public int nextMove(int opponentMove) {
        int randomBestMoveIndex = randomGenerator.nextInt(listOfBestMoves.size());
        return listOfBestMoves.get(randomBestMoveIndex);
    }
}
