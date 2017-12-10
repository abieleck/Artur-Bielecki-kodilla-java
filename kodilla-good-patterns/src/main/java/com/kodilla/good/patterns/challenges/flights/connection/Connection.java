package com.kodilla.good.patterns.challenges.flights.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Connection {

    private List<Flight> connectingFlights = new ArrayList<>();

    public Connection addFlight(Flight flight) {
        if (connectingFlights.isEmpty()) {
            connectingFlights.add(flight);
        } else {
            BiPredicate<Flight, Flight> connectingAirport =
                    (firstFlight, secondFlight) -> secondFlight.getDeparture().equals(firstFlight.getDestination());
            BiPredicate<Flight, Flight> connectingTime = (firstFlight, secondFlight)
                            -> secondFlight.getDepartureTime().isAfter(firstFlight.getArrivalTime());
            Flight lastFlight = connectingFlights.get(connectingFlights.size() - 1);
            if (connectingAirport.and(connectingTime).negate().test(lastFlight, flight)) {
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
