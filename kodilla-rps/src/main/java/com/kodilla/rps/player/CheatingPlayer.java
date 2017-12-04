package com.kodilla.rps.player;

import com.kodilla.rps.settings.Settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheatingPlayer implements ComputerPlayer {

    private Map<Integer, List<Integer>> bestResponses = new HashMap<>();
    private Random randomGenerator;
    int movesCount;

    public CheatingPlayer(Settings settings) {
        randomGenerator = new Random();
        movesCount = settings.getMovesCount();
        for(int i=1; i<=movesCount; i++) {
            int opponentMove = i;
            int bestResult = IntStream.rangeClosed(1, movesCount)
                    .map(move -> settings.getScore(move, opponentMove))
                    .max().getAsInt();

            List<Integer> responses = IntStream.rangeClosed(1, movesCount)
                    .filter(move -> settings.getScore(move, opponentMove) == bestResult)
                    .boxed()
                    .collect(Collectors.toList());
            bestResponses.put(i, responses);
        }
    }

    @Override
    public int nextMove(int opponentMove) {
        boolean cheating = randomGenerator.nextBoolean();
        if (cheating) {
            List<Integer> bestMoves = bestResponses.get(opponentMove);
            return bestMoves.get(randomGenerator.nextInt(bestMoves.size()));
        } else {
            return randomGenerator.nextInt(movesCount) + 1;
        }
    }
}
