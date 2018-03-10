package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.Observable;
import com.kodilla.patterns2.observer.Observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AssignmentsQueue implements Observable<AssignmentsQueue> {

    String name;
    Deque<Assignment> assignmentsQueue = new ArrayDeque<>();
    List<Observer<AssignmentsQueue>> observers = new ArrayList<>();

    public AssignmentsQueue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Deque<Assignment> getAssignmentsQueue() {
        return assignmentsQueue;
    }

    public void addAssignment(Assignment assignment) {
        assignmentsQueue.addLast(assignment);
        notifyObservers("Added new assignment in " + name + " queue: " + assignment);
    }

    public void removeAssignment() {
        Assignment assignment = assignmentsQueue.removeFirst();
        notifyObservers("Assignment done in " + name + " queue: " + assignment);
    }

    @Override
    public void registerObserver(Observer<AssignmentsQueue> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer<AssignmentsQueue> observer: observers) {
            observer.update(this);
        }
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer<AssignmentsQueue> observer: observers) {
            observer.update(this, message);
        }
    }

    @Override
    public void removeObserver(Observer<AssignmentsQueue> observer) {
        observers.remove(observer);
    }


}
