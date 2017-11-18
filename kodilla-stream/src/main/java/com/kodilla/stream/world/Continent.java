package com.kodilla.stream.world;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Continent {

    private final String name;
    private final Set<Country> countries = new HashSet<>(); // Set will guarantee no duplicates

    public Continent(String name, Country... countries) {
        this.name = name;
        this.countries.addAll(Arrays.asList(countries));
    }

    public String getName() {
        return name;
    }

    public Set<Country> getCountries() {
        return Collections.unmodifiableSet(countries);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Continent)) return false;

        Continent continent = (Continent) o;

        return name.equals(continent.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
