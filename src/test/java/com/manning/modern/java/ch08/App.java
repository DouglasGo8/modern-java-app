package com.manning.modern.java.ch08;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class App {


    @Test
    public void howToFactories() {


        var friendsOfStreamSet = Stream.of("Raphael", "Olivia", "Thibaut").collect(Collectors.toSet());
        var friendsOfList = List.of("Raphael", "Olivia", "Thibaut");
        var friendsOfSet = Set.of("Raphael", "Olivia", "Thibaut");
        var friendsOfMap = Map.of("Key1", 1, "Key2", 2);


        out.println(friendsOfMap);

    }

    @Test
    public void howtOSortMap() {

        var favoriteMovies = Map.ofEntries(
                Map.entry("Raphael","Start Wars"),
                Map.entry("Douglas", "The Crow"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond"));

        favoriteMovies.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(out::println);

        out.println(favoriteMovies.getOrDefault("Douglas", "The Crow"));
    }
}
