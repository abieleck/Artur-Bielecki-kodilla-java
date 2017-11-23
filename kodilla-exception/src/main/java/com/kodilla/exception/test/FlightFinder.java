package com.kodilla.exception.test;


import com.google.common.collect.HashMultimap;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

public class FlightFinder {

    private HashMultimap<Flight, LocalTime> departures = HashMultimap.create();

    public FlightFinder() {
        departures.put(new Flight("Berlin", "Warsaw"), LocalTime.of(12,33));
        departures.put(new Flight("Berlin", "Warsaw"), LocalTime.of(12,55));
        departures.put(new Flight("Berlin", "Vienna"), LocalTime.of(13,22));
        departures.put(new Flight("Warsaw", "Vienna"), LocalTime.of(14,18));
        departures.put(new Flight("Warsaw", "Brussels"), LocalTime.of(18,11));
        departures.put(new Flight("Amsterdam", "Berlin"), LocalTime.of(19,52));

    }

    public Set<LocalTime> findFlight(Flight flight) throws RouteNotFoundException {
        Set<LocalTime> departureTimes = departures.get(flight);
        if (departureTimes.isEmpty()) {
            throw new RouteNotFoundException(flight);
        }
        return Collections.unmodifiableSet(departureTimes);
    }

    public static void main(String[] args) {
        FlightFinder flightFinder = new FlightFinder();
        Flight flight = new Flight("Berlin", "New York");

        System.out.println("Flights from " + flight.getDepartureAirport() + " to " +flight.getArrivalAirport() + ":");
        try {
            flightFinder.findFlight(flight).stream()
                    .forEach(s -> System.out.print(s + " "));
            System.out.println();
        } catch (RouteNotFoundException e) {
            System.out.println("Sorry, could not find any flights");
        }

    }
}
