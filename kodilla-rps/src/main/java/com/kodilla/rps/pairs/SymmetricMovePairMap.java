package com.kodilla.rps.pairs;

public class SymmetricMovePairMap<T> extends MovePairMap<T> {

    public SymmetricMovePairMap(int movesCount, T defaultValue) {
        super(movesCount, defaultValue);
    }

    @Override
    public T get(int move1, int move2) {
        if (!validateMovePair(move1, move2)) {
            throw new IllegalArgumentException();
        }
        return pairMap.get(MovePair.getOrderedPair(move1, move2));
    }

    @Override
    public T put(int move1, int move2, T value) {
        if (!validateMovePair(move1, move2)) {
            throw new IllegalArgumentException();
        }
        return pairMap.put(MovePair.getOrderedPair(move1, move2), value);
    }

    @Override
    public MovePairMap clone() {
        SymmetricMovePairMap<T> myClone = new SymmetricMovePairMap<>(movesCount, null);
        for(MovePair p: pairMap.keySet()) {
            myClone.put(p.move1, p.move2, pairMap.get(p));
        }
        return myClone;
    }
}
