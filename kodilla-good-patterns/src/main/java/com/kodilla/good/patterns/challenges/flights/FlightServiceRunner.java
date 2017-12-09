package com.kodilla.good.patterns.challenges.flights;

import com.kodilla.good.patterns.challenges.flights.connection.Airport;
import com.kodilla.good.patterns.challenges.flights.connection.Connection;
import com.kodilla.good.patterns.challenges.flights.connection.FlightRepository;
import com.kodilla.good.patterns.challenges.flights.connection.FlightRepositoryCsv;
import com.kodilla.good.patterns.challenges.flights.menu.AppStatus;
import com.kodilla.good.patterns.challenges.flights.menu.EndApplicationMenu;
import com.kodilla.good.patterns.challenges.flights.menu.Menu;
import com.kodilla.good.patterns.challenges.flights.menu.MenuItem;

import java.util.List;

public class FlightServiceRunner {

    private static FlightRepository flightRepository = new FlightRepositoryCsv();
    private static FlightService flightService = new FlightService(flightRepository);

    private static Menu<AppStatus> menu = new Menu<>("Available options:", "Select your choice");
    private static EndApplicationMenu<AppStatus> endMenu = new EndApplicationMenu<>(menu, AppStatus.END_APPLICATION);

    private static void initializeMenu() {
        menu.addItem(new MenuItem<>('f', "find flights from city", FlightServiceRunner::findFlightsFrom));
        menu.addItem(new MenuItem<>('t', "find flights to city", FlightServiceRunner::findFlightsTo));
        menu.addItem(new MenuItem<>('b', "find flights between cities",
                FlightServiceRunner::findFlightsBetween));
        menu.addItem(new MenuItem<>('x', "exit application", endMenu::execute));
    }

    private static void showConnections(List<Connection> connections, String heading) {
        if (connections.size() == 0) {
            System.out.println("Sorry, we did not find any flights.");
        } else {
            System.out.println(heading);
            connections.stream().forEach(System.out::println);
        }
    }

    private static AppStatus findFlightsFrom() {
        String cityFrom = Input.getInput("Enter city of departure: ", ".+");
        List<Connection> connections = flightService.getFlightsFrom(new Airport(cityFrom));
        showConnections(connections, "Flights from " + cityFrom + ":");
        return AppStatus.NORMAL;
    }

    private static AppStatus findFlightsTo() {
        String cityTo = Input.getInput("Enter city of destination: ", ".+");
        List<Connection> connections = flightService.getFlightsTo(new Airport(cityTo));
        showConnections(connections, "Flights to " + cityTo + ":");
        return AppStatus.NORMAL;
    }

    private static AppStatus findFlightsBetween() {
        String cityFrom = Input.getInput("Enter city of departure: ", ".+");
        String cityTo = Input.getInput("Enter city of destination: ", ".+");
        String directFlightsChoice = Input.getInput("Only direct flights (y/n)?", "^(y|n)$");
        List<Connection> connections;
        if (directFlightsChoice.equals("y")) {
            connections = flightService.getDirectFlightsBetween(new Airport(cityFrom), new Airport(cityTo));
        } else {
            connections = flightService.getFlightsBetween(new Airport(cityFrom), new Airport(cityTo));
        }
        showConnections(connections, "Flights between " + cityFrom + " and " + cityTo + ":");
        return AppStatus.NORMAL;
    }

    public static void main(String[] args) {
        initializeMenu();
        Input.open();
        AppStatus appStatus;
        do {
            appStatus = menu.execute();
        } while (appStatus != AppStatus.END_APPLICATION);
        Input.close();
    }
}
