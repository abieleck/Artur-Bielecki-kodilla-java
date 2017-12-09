package com.kodilla.good.patterns.challenges.flights.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Connection {

    private List<Flight> connectingFlights = new ArrayList<>();

    public Connection addFlight(Flight flight) {
        if (connectingFlights.isEmpty()) {
            connectingFlights.add(flight);
        } else {
            Flight lastFlight = connectingFlights.get(connectingFlights.size() - 1);
            if (!flight.getDeparture().equals(lastFlight.getDestination()) ||
                    flight.getDepartureTime().isBefore(lastFlight.getArrivalTime())) {
                throw new UnmatchedConnectingFlightException();
            }
            connectingFlights.add(flight);
        }
        return this;
    }

    @Override
    public String toString() {
        return connectingFlights.stream()
                .map(Flight::toString)
                .collect(Collectors.joining(" next: "));
    }

}
