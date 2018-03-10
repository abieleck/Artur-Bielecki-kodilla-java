package com.kodilla.patterns2.observer;

public interface Observer<T> {
    void update(T source);
    void update(T source, String message);
}
