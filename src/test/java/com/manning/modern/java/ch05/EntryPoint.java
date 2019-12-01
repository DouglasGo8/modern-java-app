package com.manning.modern.java.ch05;

import com.manning.modern.java.domain.dto.Dish;
import com.manning.modern.java.domain.Trader;
import com.manning.modern.java.domain.dto.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

/**
 *
 */
public class EntryPoint {


    @Test
    public void filteringVegetarians() {

        Dish.listOfMenu()
                .stream()
                .filter(Dish::isVegetarian)
                .forEach(out::println);

    }

    @Test
    public void distinctElements() {

        Stream.of(1, 2, 1, 3, 4, 2, 4)
                .filter(n -> n % 2 == 0)
                .distinct()
                .forEach(out::println);

    }

    /**
     * Java 9 up feature
     */
    @Test
    public void takeWhileCalories() {

        Dish.listOfSpecialMen()
                .stream()
                .takeWhile(d -> d.getCalories() < 320)
                .forEach(out::println);

    }

    /**
     * Java 9 up feature
     */
    @Test
    public void dropWhileCalories() {

        Dish.listOfSpecialMen()
                .stream()
                .dropWhile(d -> d.getCalories() < 320)
                .forEach(out::println);

    }


    /**
     * Java 9 up feature
     */
    @Test
    public void skipElements() {

        Dish.listOfMenu()
                .stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .forEach(out::println);

    }

    @Test
    public void transformWithMap() {

        Stream.of("Modern", "Java", "in", "Action")
                .map(String::length)
                .forEach(out::println);
    }

    @Test
    public void flatteningStreams() {

        Stream.of("Hello", "World")
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(out::println);


    }

    @Test
    public void flatMapWithInts() {

        Stream.of(1, 2, 3)
                .flatMap(n1 -> Stream.of(3, 4).filter(n2 -> (n1 + n2) % 3 == 0).map(n2 -> new int[]{n1, n2}))
                .flatMapToInt(Arrays::stream)
                .distinct()
                .forEach(out::println);

    }

    @Test
    public void matchAndFindElements() {

        out.println(Dish.listOfMenu().stream().anyMatch(Dish::isVegetarian));
        out.println(Dish.listOfMenu().stream().allMatch(dish -> dish.getCalories() < 100));
        out.println(Dish.listOfMenu().stream().noneMatch(dish -> dish.getCalories() >= 1000));

        Dish.listOfMenu().stream().filter(Dish::isVegetarian).findAny().ifPresent(out::println);
        Dish.listOfMenu().stream().filter(Dish::isVegetarian).findFirst().ifPresent(out::println);

    }

    @Test
    public void reducing() {

        out.println(IntStream.range(1, 20).reduce(Integer::sum));
        out.println(IntStream.range(1, 20).reduce(0, Integer::sum));
        out.println(IntStream.range(1, 20).reduce(0, Integer::max));
        out.println(IntStream.range(1, 20).reduce(Integer::min));

        out.println(IntStream.range(1, 20).parallel().reduce(Integer::sum));


        out.println(Dish.listOfMenu()
                .stream()
                .map(d -> 1)
                .reduce(Integer::sum)); // solved with count
    }


    @Test
    public void findAllTransactionsIn2011() {

        Transaction.listOfTransactions()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(out::println);
    }

    @Test
    public void whatAreUniqueCities() {

        Transaction.listOfTransactions()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(out::println);

        Transaction.listOfTransactions()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet())
                .forEach(out::println);
    }

    @Test
    public void findAllTradesFromCambridgeAndSortThem() {

        Transaction.listOfTransactions()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(out::println);
    }

    @Test
    public void returnAllStringsOfTraders() {

        out.println(Transaction.listOfTransactions()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .sorted()
                .reduce(String::concat));

        out.println(Transaction.listOfTransactions()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .sorted()
                .collect(Collectors.joining()));

    }

    @Test
    public void anyTradersBasedOn() {

        Transaction.listOfTransactions()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(out::println);

        out.println(Transaction.listOfTransactions()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan")));


    }

    @Test
    public void reducingTransactionsAndTraders() {

        out.print(Transaction.listOfTransactions()
                .map(Transaction::getValue)
                .reduce(Integer::max));

        out.print(Transaction.listOfTransactions()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2));

        out.print(Transaction.listOfTransactions()
                .min(Comparator.comparing(Transaction::getValue)));


    }

}
