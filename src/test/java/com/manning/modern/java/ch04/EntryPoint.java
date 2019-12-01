package com.manning.modern.java.ch04;


import com.manning.modern.java.domain.dto.Dish;
import org.junit.Test;

import static java.lang.System.out;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 */
public class EntryPoint {

    @Test
    public void afterJava8() {

        Dish.listOfMenu().stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .forEach(out::println);


    }

    @Test
    public void groupingByType() {

        Dish.listOfMenu().stream()
                .collect(groupingBy(Dish::getType))
                .forEach((k, v) -> out.println(String.format("key: %s - value(s) %s", k, v)));



    }

    @Test
    public void limitingResult() {
        Dish.listOfMenu().stream()
                .filter(d -> d.getCalories() > 300)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .limit(2)
                .forEach(out::println);

    }

}
