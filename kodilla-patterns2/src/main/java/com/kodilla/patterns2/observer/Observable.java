package com.kodilla.patterns2.observer;

public interface Observable<T> {
    void registerObserver(Observer<T> observer);
    void notifyObservers();
    void notifyObservers(String message);
    void removeObserver(Observer<T> observer);
}
