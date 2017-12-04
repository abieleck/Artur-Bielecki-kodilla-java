package com.kodilla.rps.player;

import com.kodilla.rps.pairs.MovePair;
import com.kodilla.rps.settings.Settings;

import java.util.*;
import java.util.stream.Collectors;

public class ImitatePlayer implements ComputerPlayer {

    private HashMap<MovePair, Integer> responsesCount = new HashMap<>();
    private Integer myPreviousMove;
    private int movesCount;
    Random randomGenerator = new Random();

    public ImitatePlayer(Settings settings) {
        movesCount = settings.getMovesCount();
    }

    @Override
    public int nextMove(int opponentMove) {
        int move;

        OptionalInt mostFrequentResponseCountOptional = responsesCount.keySet().stream()
                .filter(key -> myPreviousMove != null && key.getMove1() == myPreviousMove)
                .mapToInt(key -> responsesCount.get(key))
                .max();
        if (mostFrequentResponseCountOptional.isPresent()) {
            int mostFrequentResponseCount = mostFrequentResponseCountOptional.getAsInt();
            List<Integer> responses = responsesCount.keySet().stream()
                    .filter(key -> myPreviousMove != null && key.getMove1() == myPreviousMove)
                    .filter(key -> responsesCount.get(key).equals(mostFrequentResponseCount))
                    .map(key -> key.getMove2())
                    .collect(Collectors.toList());
            move = responses.get(randomGenerator.nextInt(responses.size()));
        } else {
            move = randomGenerator.nextInt(movesCount) + 1;
        }
        if (myPreviousMove != null) {
            MovePair mapKey = new MovePair(myPreviousMove, opponentMove);
            responsesCount.put(mapKey, responsesCount.getOrDefault(mapKey, 0) + 1);
        }
        myPreviousMove = move;
        return move;
    }
}
