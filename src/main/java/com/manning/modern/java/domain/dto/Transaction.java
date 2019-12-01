package com.manning.modern.java.domain.dto;

import com.manning.modern.java.domain.Trader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class Transaction {

    private final Trader trader;

    private final int year;
    private final int value;


    public static Stream<Transaction> listOfTransactions() {

        final Trader raoul = new Trader("Raoul", "Cambridge");
        final Trader mario = new Trader("Mario", "Milan");
        final Trader alan = new Trader("Alan", "Cambridge");
        final Trader brian = new Trader("Brian", "Cambridge");

        return Stream.of(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";
    }
}
