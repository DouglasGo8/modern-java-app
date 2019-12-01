package com.manning.modern.java.domain;

import com.manning.modern.java.domain.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 */
@Data
@AllArgsConstructor
public class Apple {
    private final int weight;
    private final Color color;
}
