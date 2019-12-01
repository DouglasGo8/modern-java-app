package com.manning.modern.java.ch02;

import com.manning.modern.java.domain.Apple;
import com.manning.modern.java.domain.enums.Color;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static java.lang.System.out;
/**
 * @author dbatista
 */
public class EntryPoint {


    @Test
    @Ignore
    public void filterAppleGreen() {
        List.of(new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED))
                .stream()
                .filter(a -> a.getWeight() > 150)
                .forEach(out::println);
    }
}
