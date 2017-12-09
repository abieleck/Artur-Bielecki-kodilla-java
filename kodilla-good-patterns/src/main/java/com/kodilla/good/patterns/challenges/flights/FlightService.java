package com.kodilla.good.patterns.challenges.flights;

import com.kodilla.good.patterns.challenges.flights.connection.Airport;
import com.kodilla.good.patterns.challenges.flights.connection.Connection;
import com.kodilla.good.patterns.challenges.flights.connection.Flight;
import com.kodilla.good.patterns.challenges.flights.connection.FlightRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService {

    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Connection> getFlightsFrom(Airport cityFrom) {
        return flightRepository.getFlights().stream()
                .filter(flight -> flight.getDeparture().equals(cityFrom))
                .map(flight -> (new Connection()).addFlight(flight))
                .collect(Collectors.toList());
    }

    public List<Connection> getFlightsTo(Airport cityTo) {
        return flightRepository.getFlights().stream()
                .filter(flight -> flight.getDestination().equals(cityTo))
                .map(flight -> (new Connection()).addFlight(flight))
                .collect(Collectors.toList());
    }

    public List<Connection> getDirectFlightsBetween(Airport cityFrom, Airport cityTo) {
        return flightRepository.getFlights().stream()
                .filter(flight -> flight.getDeparture().equals(cityFrom) && flight.getDestination().equals(cityTo))
                .map(flight -> (new Connection()).addFlight(flight))
                .collect(Collectors.toList());
    }

    public List<Connection> getFlightsBetween(Airport cityFrom, Airport cityTo) {

        List<Connection> connections = getDirectFlightsBetween(cityFrom, cityTo);

        List<Flight> flights = flightRepository.getFlights();

        List<Connection> indirectConnections = flights.stream()
                .filter(flight -> flight.getDeparture().equals(cityFrom))
                .flatMap(flight -> flights.stream()
                        .filter(
                                f -> f.getDeparture().equals(flight.getDestination())
                                && f.getDestination().equals(cityTo)
                                && f.getDepartureTime().isAfter(flight.getArrivalTime()))
                            .map(f -> (new Connection()).addFlight(flight).addFlight(f)))
                .collect(Collectors.toList());

        connections.addAll(indirectConnections);
        return connections;
    }
}
