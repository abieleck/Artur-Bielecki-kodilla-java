package com.kodilla.testing.collection;

import java.util.ArrayList;

public class OddNumbersExterminator {

    ArrayList<Integer> exterminate(ArrayList<Integer> numbers) {

        ArrayList<Integer> result = new ArrayList<>();
        // świadomie nie sprawdzam czy numbers == null :)
        for(int i: numbers) {
            if (i % 2 == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
