package com.kodilla.rps.pairs;

public class AsymmetricMovePairMap extends MovePairMap<Integer> {

    public AsymmetricMovePairMap(int movesCount, Integer defaultValue) {
        super(movesCount, defaultValue);
    }

    @Override
    public Integer get(int move1, int move2) {
        if (!validateMovePair(move1, move2)) {
            throw new IllegalArgumentException();
        }
        if (move1 < move2) {
            return pairMap.get(MovePair.getOrderedPair(move1, move2));
        } else {
            return -pairMap.get(MovePair.getOrderedPair(move1, move2));
        }
    }

    @Override
    public Integer put(int move1, int move2, Integer value) {
        if (!validateMovePair(move1, move2)) {
            throw new IllegalArgumentException();
        }
        if (move1 < move2) {
            return pairMap.put(MovePair.getOrderedPair(move1, move2), value);
        } else {
            return -pairMap.put(MovePair.getOrderedPair(move1, move2), -value);
        }
    }

    @Override
    public MovePairMap clone() {
        AsymmetricMovePairMap myClone = new AsymmetricMovePairMap(movesCount, null);
        for(MovePair p: pairMap.keySet()) {
            myClone.put(p.move1, p.move2, pairMap.get(p));
        }
        return myClone;
    }
}
