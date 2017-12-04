package com.kodilla.rps.settings;

import com.kodilla.rps.pairs.AsymmetricMovePairMap;
import com.kodilla.rps.pairs.MovePairMap;
import com.kodilla.rps.pairs.SymmetricMovePairMap;
import com.kodilla.rps.player.ComputerPlayer;
import com.kodilla.rps.player.PlayerType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Settings {

    private PlayerType computerStrategy;
    private List<String> moveNames = new ArrayList<>();
    private MovePairMap<Integer> scores;
    private MovePairMap<String> scoreDescriptions;

    public Settings() {
        restoreDefaults();
    }

    public Settings(Settings settings) {
        this.computerStrategy = settings.computerStrategy;
        this.moveNames = new ArrayList<>(settings.moveNames);
        this.scores = settings.scores.clone();
        this.scoreDescriptions = settings.scoreDescriptions.clone();
    }

    public void setComputerStrategy(PlayerType newStrategy) {
        this.computerStrategy = newStrategy;
    }

    public Settings(int movesCount) {
        scores = new AsymmetricMovePairMap(movesCount, 0);
        scoreDescriptions = new SymmetricMovePairMap<String>(movesCount, "");
        computerStrategy = PlayerType.RANDOM;
        moveNames = Arrays.asList(new String[movesCount]);
    }

    public int getMovesCount() {
        return moveNames.size();
    }

    public String getMoveName(int move) {
        if(validateMoveNumber(move)) {
            return moveNames.get(move-1);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getResultDescription(int move1, int move2) {
        if (move1 == move2) {
            return "";
        }
        return scoreDescriptions.get(move1, move2);
    }

    public void insertBlankMove(int newMove) {
        moveNames.add(newMove-1, "");
        scores.insertNewMove(newMove, 0);
        scoreDescriptions.insertNewMove(newMove, "");
    }

    public void replaceMoveName(int moveNumber, String newMoveName) {
        if(moveNumber < 1 || moveNumber > getMovesCount()) {
            throw new IllegalArgumentException();
        }
        moveNames.set(moveNumber-1, newMoveName);
    }

    public void replaceScore(int move1, int move2, int score) {
        scores.put(move1, move2, score);
    }

    public void replaceScoreDescription(int move1, int move2, String scoreDescription) {
        scoreDescriptions.put(move1, move2, scoreDescription);
    }

    public ComputerPlayer getComputerPlayer() {
        return computerStrategy.getNewComputerPlayer(this);
    }

    public boolean validateMoveNumber(int moveNumber) {
        return moveNumber >= 1 && moveNumber <= moveNames.size();
    }

    public int getScore(int move1, int move2) {
        if (move1 == move2) {
            return 0;
        }
        return scores.get(move1, move2);
    }

    public PlayerType getComputerStrategy() {
        return computerStrategy;
    }

    protected void restoreDefaults() {
        moveNames.clear();
        moveNames.add("rock");
        moveNames.add("paper");
        moveNames.add("scissors");

        scores = new AsymmetricMovePairMap(3, 0);
        scores.put(1,2, -1);
        scores.put(1,3, 1);
        scores.put(2,3, -1);

        scoreDescriptions = new SymmetricMovePairMap<>(3, "");
        scoreDescriptions.put(1,2, "Paper covers rock.");
        scoreDescriptions.put(1,3, "Rock crushes scissors.");
        scoreDescriptions.put(2,3, "Scissors cut paper.");

        computerStrategy = PlayerType.RANDOM;
    }

    protected void deleteMove(int move) {
        moveNames.remove(move-1);
        scores.deleteMove(move);
        scoreDescriptions.deleteMove(move);
    }

    protected String movesToString() {
        return IntStream.rangeClosed(1, moveNames.size())
                .mapToObj(i -> "'" + i + "' - " + moveNames.get(i-1))
                .collect(Collectors.joining("\n"));
    }

    protected String scoresToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1; i<getMovesCount(); i++)
            for(int j=i+1; j<=getMovesCount(); j++) {
                stringBuilder.append(moveNames.get(i-1) + " (" + i + ") - " + moveNames.get(j-1) + " (" + j + ") ");
                int result1stPlayer = scores.get(i, j);
                int result2ndPlayer = 0;
                if (result1stPlayer < 0) {
                    result2ndPlayer = -result1stPlayer;
                    result1stPlayer = 0;
                }
                stringBuilder.append(Integer.toString(result1stPlayer) + ':' + result2ndPlayer + ". ");
                stringBuilder.append(scoreDescriptions.get(i, j));
                if(i!=getMovesCount()-1 || j!=getMovesCount()) {
                    stringBuilder.append('\n');
                }
            }
        return stringBuilder.toString();
    }

    protected boolean moveNameDefined(String moveName) {
        return moveNames.contains(moveName);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Computer strategy: " + computerStrategy + '\n');
        stringBuilder.append("Available moves:\n");
        stringBuilder.append(movesToString() + '\n');
        stringBuilder.append("Scores (identical moves result in a draw 0:0 and are not shown):\n");
        stringBuilder.append(scoresToString());
        return stringBuilder.toString();
    }
}
