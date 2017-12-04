package com.kodilla.rps.pairs;

import java.util.HashMap;

public abstract class MovePairMap<T> {

    protected int movesCount;
    protected HashMap<MovePair, T> pairMap = new HashMap<>();

    protected MovePairMap(int movesCount, T defaultValue) {
        this.movesCount = movesCount;
        for(int i=1; i<movesCount; i++)
            for(int j=i+1; j<=movesCount; j++) {
                pairMap.put(new MovePair(i,j), defaultValue);
            }
    }

    protected boolean validateMove(int move) {
        return move>= 1 && move <= movesCount;
    }

    protected boolean validateMovePair(int move1, int move2) {
        return validateMove(move1) && validateMove(move2) && move1 != move2;
    }

    public abstract T get(int move1, int move2);

    public abstract T put(int move1, int move2, T value);

    public void insertNewMove(int newMove, T defaultValue) {
        movesCount++;
        if (!validateMove(newMove)) {
            throw new IllegalArgumentException();
        }
        HashMap<MovePair, T> newPairMap = new HashMap<>();
        for(int i=1; i<movesCount; i++)
            for(int j=i+1; j<= movesCount; j++) {
                if(i==newMove || j==newMove) {
                    newPairMap.put(new MovePair(i,j), defaultValue);
                } else {
                    int oldI = (i>newMove) ? i-1 : i;
                    int oldJ = (j>newMove) ? j-1 : j;
                    newPairMap.put(new MovePair(i,j), pairMap.get(new MovePair(oldI, oldJ)));
                }
            }
        pairMap = newPairMap;
    }

    public void deleteMove(int deletedMove) {
        if (!validateMove(deletedMove)) {
            throw new IllegalArgumentException();
        }
        movesCount--;
        HashMap<MovePair, T> newPairMap = new HashMap<>();
        for(int i=1; i<movesCount; i++)
            for(int j=i+1; j<= movesCount; j++) {
                int oldI = (i>=deletedMove) ? i+1 : i;
                int oldJ = (j>=deletedMove) ? j+1 : j;
                newPairMap.put(new MovePair(i,j), pairMap.get(new MovePair(oldI, oldJ)));
            }
        pairMap = newPairMap;
    }

    public abstract MovePairMap clone();
}
