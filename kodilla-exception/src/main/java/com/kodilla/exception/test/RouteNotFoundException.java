package com.kodilla.exception.test;

public class RouteNotFoundException extends Exception {
    public RouteNotFoundException(Flight flight) {
        super("Attempt to get flights for non-existent connection from "
                + flight.getDepartureAirport() + " to " + flight.getArrivalAirport());
    }
}
