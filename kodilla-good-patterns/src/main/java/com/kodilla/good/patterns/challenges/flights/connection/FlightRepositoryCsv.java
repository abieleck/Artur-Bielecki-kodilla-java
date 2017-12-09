package com.kodilla.good.patterns.challenges.flights.connection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

    private LocalDateTime readTime(String timeString) {
        String[] dateTime = timeString.split(" ");
        String[] dateFields = dateTime[0].split("-");
        String[] timeFields = dateTime[1].split(":");
        int year = Integer.parseInt(dateFields[0]);
        int month = Integer.parseInt(dateFields[1]);
        int day = Integer.parseInt(dateFields[2]);
        int hour = Integer.parseInt(timeFields[0]);
        int minute = Integer.parseInt(timeFields[1]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    private Flight readFlightFromString(String csvLine) {

        String[] columns = csvLine.split(";");
        Airport departure = new Airport(columns[0]);
        Airport destination = new Airport(columns[2]);
        LocalDateTime departureTime = readTime(columns[1]);
        LocalDateTime arrivalTime = readTime(columns[3]);
        Flight flight = new Flight(departure, destination, departureTime, arrivalTime);
        return flight;
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

}
