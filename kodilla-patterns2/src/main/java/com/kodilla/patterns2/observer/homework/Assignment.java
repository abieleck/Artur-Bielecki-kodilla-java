package com.kodilla.patterns2.observer.homework;

import java.util.Objects;

public final class Assignment {
    private final String subject;

    public Assignment(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject);
    }
}
