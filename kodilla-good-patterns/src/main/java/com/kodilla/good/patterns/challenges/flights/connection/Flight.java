package com.kodilla.good.patterns.challenges.flights.connection;

import java.time.LocalDateTime;
import java.util.Objects;

public final class Flight {

    private final Airport departure;
    private final Airport destination;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;

    public Flight(Airport departure, Airport destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        if (!arrivalTime.isAfter(departureTime)) {
            throw new IncorrectFlightSequenceException();
        }
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(departure, flight.departure) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(arrivalTime, flight.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, departureTime, arrivalTime);
    }

    public Airport getDeparture() {
        return departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return departure + " (" + departureTime.toString().replace('T', ' ') + ") - "
                + destination + " (" + arrivalTime.toString().replace('T', ' ') + ")";
    }
}
