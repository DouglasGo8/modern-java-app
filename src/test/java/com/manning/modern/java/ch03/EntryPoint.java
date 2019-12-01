package com.manning.modern.java.ch03;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 *
 */
public class EntryPoint {


    @Test
    public void runnableWithLambda() throws Exception {

        new Thread(() -> out.println("flight")).start();

        Executors.newFixedThreadPool(2)
                .submit(() -> out.println("into the Night"));

        Executors.callable(() -> out.println("If you can Flight")).call();

        Executors.newScheduledThreadPool(2)
                .schedule(() -> "hold On ;-)", 100L, TimeUnit.MILLISECONDS);


        TimeUnit.MILLISECONDS.sleep(200);

    }

}
