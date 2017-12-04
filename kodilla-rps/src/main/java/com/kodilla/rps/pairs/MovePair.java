package com.kodilla.rps.pairs;

import java.util.Objects;

public class MovePair {
    int move1;
    int move2;

    public MovePair(int move1, int move2) {
        this.move1 = move1;
        this.move2 = move2;
    }

    public int getMove1() {
        return move1;
    }

    public int getMove2() {
        return move2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovePair MovePair = (MovePair) o;
        return move1 == MovePair.move1 &&
                move2 == MovePair.move2;
    }

    @Override
    public int hashCode() {

        return Objects.hash(move1, move2);
    }

    public static MovePair getOrderedPair(int move1, int move2) {
        if (move1 <= move2) {
            return new MovePair(move1, move2);
        } else {
            return new MovePair(move2, move1);
        }

    }
}
