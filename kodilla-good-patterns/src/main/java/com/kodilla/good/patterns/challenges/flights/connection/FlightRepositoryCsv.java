package com.kodilla.good.patterns.challenges.flights.connection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightRepositoryCsv implements FlightRepository {

    private final static String ORDERS_FILENAME = "file/Flights.csv";

    List<Flight> flights;

    public FlightRepositoryCsv() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(ORDERS_FILENAME).getFile());
        Path path = Paths.get(file.getPath());
        try {
            flights = Files.lines(path)
                    .skip(1)
                    .map(this::readFlightFromString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            flights = new ArrayList<>();
        }
    }

    private Flight readFlightFromString(String csvLine) {

        String[] columns = csvLine.split(";");
        Airport departure = new Airport(columns[0]);
        Airport destination = new Airport(columns[2]);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        LocalDateTime departureTime = LocalDateTime.parse(columns[1], dateTimeFormatter);
        LocalDateTime arrivalTime = LocalDateTime.parse(columns[3], dateTimeFormatter);
        Flight flight = new Flight(departure, destination, departureTime, arrivalTime);
        return flight;
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

}
