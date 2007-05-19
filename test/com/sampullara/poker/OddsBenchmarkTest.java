package com.sampullara.poker;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Mar 9, 2007
 * Time: 8:37:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class OddsBenchmarkTest {
    public static void main(String[] args) throws InterruptedException {
        int cpus = Runtime.getRuntime().availableProcessors();
        if (args.length != 0) {
            cpus = Integer.parseInt(args[0]);
        }
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(cpus);
        for (int i = 0; i < cpus; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    OddsTest test = new OddsTest();
                    test.testPreflopOdds();
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.SECONDS);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(3400000*cpus/elapsed*1000 + " evals/second");
    }
}