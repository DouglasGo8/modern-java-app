package com.manning.modern.java.domain.dto;

import com.manning.modern.java.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * @author dbatista
 */
@Data
@AllArgsConstructor
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public static List<Dish> listOfMenu() {

        return List.of(new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH));
    }

    public static List<Dish> listOfSpecialMen() {

        return List.of(new Dish("seasonal fruit", true, 120, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER));
    }

    public static Map<String, List<String>> listOfDishes() {
        return new HashMap<>() {{
            put("pork", asList("greasy", "salty"));
            put("beef", asList("salty", "roasted"));
            put("chicken", asList("fried", "crisp"));
            put("french fries", asList("greasy", "fried"));
            put("rice", asList("light", "natural"));
            put("season fruit", asList("fresh", "natural"));
            put("pizza", asList("tasty", "salty"));
            put("prawns", asList("tasty", "roasted"));
            put("salmon", asList("delicious", "fresh"));
        }};
    }

    @Override
    public String toString() {
        return name;
    }
}
