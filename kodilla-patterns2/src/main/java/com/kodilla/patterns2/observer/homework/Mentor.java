package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.Observer;

import java.util.Objects;

public class Mentor implements Observer<AssignmentsQueue> {
    private String name;
    private int updateCount;

    public Mentor(String name) {
        this.name = name;
    }

    @Override
    public void update(AssignmentsQueue source) {
        System.out.println("Mentor " + name + " received update from assignment queue: " + source.getName());
        updateCount++;
    }

    @Override
    public void update(AssignmentsQueue source, String message) {
        System.out.println(name + " received update from assignment queue " +
                source.getName() + ": " + message);
        updateCount++;
    }

    public String getName() {
        return name;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return Objects.equals(name, mentor.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
