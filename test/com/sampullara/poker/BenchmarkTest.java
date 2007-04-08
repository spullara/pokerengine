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
public class BenchmarkTest extends TestCase {
    private static int ITERATIONS = 100000;

    public void testGameSpeed() {
        getHoldemGameSpeed();
    }

    private long getHoldemGameSpeed() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            Deck deck = new Deck();
            deck.shuffle();
            List<Hand> hands = new ArrayList<Hand>(10);
            for (int j = 0; j < 10; j++) {
                Hand hand = new Hand();
                hand.addCard(deck.deal());
                hands.add(hand);
            }
            for (int j = 0; j < 10; j++) {
                hands.get(j).addCard(deck.deal());
            }
            Board board = new Board();
            deck.burn();
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            Hand winner = null;
            HandRank winningRank = null;
            for (Hand hand : hands) {
                HandRank rank = Evaluate.holdem(hand, board);
                if (winner == null) {
                    winner = hand;
                    winningRank = rank;
                } else {
                    if (winningRank.compareTo(rank) < 0) {
                        winner = hand;
                        winningRank = rank;
                    }
                }
            }
        }
        long duration = System.currentTimeMillis() - start;
        return ITERATIONS * 1000 / duration;
    }

    public void testOmahaGameSpeed() {
        getOmahaGameSpeed();
    }

    private long getOmahaGameSpeed() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS/100; i++) {
            Deck deck = new Deck();
            deck.shuffle();
            List<Hand> hands = new ArrayList<Hand>(10);
            for (int j = 0; j < 10; j++) {
                Hand hand = new Hand();
                hand.addCard(deck.deal());
                hands.add(hand);
            }
            for (int j = 0; j < 10; j++) {
                hands.get(j).addCard(deck.deal());
            }
            for (int j = 0; j < 10; j++) {
                hands.get(j).addCard(deck.deal());
            }
            for (int j = 0; j < 10; j++) {
                hands.get(j).addCard(deck.deal());
            }
            Board board = new Board();
            deck.burn();
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            Hand winner = null;
            HandRank winningRank = null;
            for (Hand hand : hands) {
                HandRank rank = Evaluate.omaha(hand, board);
                if (winner == null) {
                    winner = hand;
                    winningRank = rank;
                } else {
                    if (winningRank.compareTo(rank) < 0) {
                        winner = hand;
                        winningRank = rank;
                    }
                }
            }
        }
        long duration = System.currentTimeMillis() - start;
        return ITERATIONS / 100 * 1000 / duration;
    }

    private static int TOTAL = 100000;

    public void testEvaluations() {
        getEvaluations();
    }

    private long getEvaluations() {
        List<Hand> hands = new ArrayList<Hand>(TOTAL);
        List<Board> boards = new ArrayList<Board>(TOTAL);
        for (int i = 0; i < TOTAL; i++) {
            Deck deck = new Deck();
            deck.shuffle();
            Hand hand = new Hand();
            hand.addCard(deck.deal());
            hand.addCard(deck.deal());
            Board board = new Board();
            deck.burn();
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            deck.burn();
            board.addCard(deck.deal());
            hands.add(hand);
            boards.add(board);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < TOTAL; i++) {
            Hand hand = hands.get(i);
            Board board = boards.get(i);
            HandRank rank = Evaluate.holdem(hand, board);
        }
        for (int i = 0; i < TOTAL; i++) {
            Hand hand = hands.get(i);
            Board board = boards.get(i);
            HandRank rank = Evaluate.holdem(hand, board);
        }
        for (int i = 0; i < TOTAL; i++) {
            Hand hand = hands.get(i);
            Board board = boards.get(i);
            HandRank rank = Evaluate.holdem(hand, board);
        }
        long duration = System.currentTimeMillis() - start;
        return 3 * TOTAL * 1000 / duration;
    }

    public static void main(String[] args) throws InterruptedException {
        int cpus = Runtime.getRuntime().availableProcessors();
        if (args.length != 0) {
            cpus = Integer.parseInt(args[0]);
        }
        ITERATIONS = 200000;
        TOTAL = 200000;
        final int[] games = new int[1];
        final int[] ranks = new int[1];
        final int[] omaha = new int[1];
        ExecutorService executor = Executors.newFixedThreadPool(cpus);
        for (int i = 0; i < cpus; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    BenchmarkTest benchmark = new BenchmarkTest();
                    long g = benchmark.getHoldemGameSpeed();
                    long r = benchmark.getEvaluations();
                    long o = benchmark.getOmahaGameSpeed();
                    games[0] += g;
                    ranks[0] += r;
                    omaha[0] += o;
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.SECONDS);
        System.out.println(games[0] + " holdem games/second");
        System.out.println(ranks[0] + " holdem ranks/second");
        System.out.println(omaha[0] + " omaha games/second");
    }
}
