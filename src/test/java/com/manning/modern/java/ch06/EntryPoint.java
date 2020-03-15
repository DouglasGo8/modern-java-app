package com.manning.modern.java.ch06;

import com.manning.modern.java.domain.dto.Dish;
import com.manning.modern.java.domain.dto.Transaction;
import com.manning.modern.java.domain.enums.CaloricLevel;
import com.manning.modern.java.domain.enums.Type;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;
import static java.util.Arrays.asList;

/**
 * @author dbatista
 */
public class EntryPoint {


    @Test
    public void groupByTransactionCurrency() {

        Map<Integer, List<Transaction>> var1 = Transaction.listOfTransactions()
                .collect(groupingBy(Transaction::getValue));

        out.println(var1);

    }

    @Test
    public void countByTransaction() {

        out.println(Transaction.listOfTransactions().collect(counting())); // can be replaced as bellow
        out.println(Transaction.listOfTransactions().count());
    }

    @Test
    public void findMaximunAndMinimun() {

        out.println(Dish.listOfMenu()
                .stream()
                .collect(maxBy(Comparator.comparingInt(Dish::getCalories)))
                .get().getCalories()); // same bellow

        out.println(Dish.listOfMenu().stream().max(Comparator.comparingInt(Dish::getCalories)));
    }

    @Test
    public void useSumAvg() {
        out.println(Dish.listOfMenu().stream().collect(summingInt(Dish::getCalories))); // can be replaced as bellow
        out.println(Dish.listOfMenu().stream().mapToInt(Dish::getCalories).sum());

        out.println(Dish.listOfMenu().stream().collect(averagingInt(Dish::getCalories)));

        // IntSummaryStatistics
        out.println(Dish.listOfMenu().stream().collect(summarizingInt(Dish::getCalories)));
    }

    @Test
    public void joiningStrings() {
        out.println(Dish.listOfMenu().stream().map(Dish::getName).collect(Collectors.joining()));
        out.println(Dish.listOfMenu().stream().map(Dish::getName).collect(Collectors.joining(", ")));
    }

    @Test
    public void sumWithReduction() {

        out.println(Dish.listOfMenu().stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j))); // can be replaced as bellow
        out.println(Dish.listOfMenu().stream().map(Dish::getCalories).reduce(Integer::sum));

        Dish.listOfMenu().stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                .ifPresent(out::println);

        Dish.listOfMenu().stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                .ifPresent(out::println);

        out.println(Dish.listOfMenu().stream().map(Dish::getName).reduce("", (s1, s2) -> s1 + s2));

    }

    @Test
    public void groupingBySome() {

        Map<Type, List<Dish>> var1 = Dish.listOfMenu()
                .stream().collect(groupingBy(Dish::getType));

        Map<Type, List<Dish>> var2 = Dish.listOfMenu()
                .stream()
                .filter(d -> d.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        Map<Type, List<Dish>> var3 = Dish.listOfMenu()
                .stream()
                .collect(groupingBy(Dish::getType, filtering(d -> d.getCalories() > 500, toList())));


        // Critical usage
        Map<Type, List<String>> var4 = Dish.listOfMenu()
                .stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));


        var1.forEach((k, v) -> out.println(k + "-" + v.stream().map(Dish::getName)
                .collect(Collectors.joining(", "))));

        out.println("---------");

        var2.forEach((k, v) -> out.println(k + "-" + v.stream().map(Dish::getName)
                .collect(Collectors.joining(", "))));

        out.println("---------");

        var3.forEach((k, v) -> out.println(k + "-" + v.stream().map(Dish::getName)
                .collect(Collectors.joining(", "))));

        out.println("---------");

        var4.forEach((k, v) -> out.println(k + "-" + String.join(", ", v)));

    }

    @Test
    public void getDishesNameByType() {

        var v1 = Dish.listOfDishes();
        var v2 = Dish.listOfMenu();

        out.println(v2.stream().collect(groupingBy(Dish::getType, flatMapping(d -> v1.get(d.getName()).stream(), toSet()))));

    }

    @Test
    public void mapByCaloricLevel() {

        var menu = Dish.listOfMenu();

        menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })).forEach((k, v) -> out.println(k + "-" + v));
    }

    @Test
    public void mappedByFlat() {

        var dishTags = new HashMap<String, List<String>>();
        var menu = Dish.listOfMenu();

        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));


        out.println((menu.stream().collect(groupingBy(Dish::getType,
                flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())))));


    }

    @Test
    public void mappedBySubGroup() {

        out.println(Dish.listOfMenu().stream().collect(groupingBy(Dish::getType, counting())));
        out.println(Dish.listOfMenu().stream()
                .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories)))));

        out.println(Dish.listOfMenu().stream().collect(toMap(Dish::getType, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)))));

        out.println(Dish.listOfMenu().stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories))));

    }

    @Test
    public void partitioningByType() {

        var menu = Dish.listOfMenu();

        out.println(menu.stream().collect(partitioningBy(Dish::isVegetarian)));
        out.println(menu.stream().filter(Dish::isVegetarian).collect(toList()));
        out.println(menu.stream().collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get))));

;
    }

}
