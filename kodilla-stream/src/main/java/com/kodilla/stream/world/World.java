package com.kodilla.stream.world;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class World {
    private final String name;
    private final Set<Continent> continents = new HashSet<>(); // Set will guarantee no duplicates

    public World(String name, Continent... continents) {
        this.name = name;
        this.continents.addAll(Arrays.asList(continents));
    }

    public String getName() {
        return name;
    }

    public Set<Continent> getContinents() {
        return Collections.unmodifiableSet(continents);
    }

    public BigDecimal getPeopleQuantity() {
        return continents.stream()
                .flatMap(continent -> continent.getCountries().stream())
                .map(Country::getPeopleQuantity)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }
}
