package com.sampullara.poker;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Mar 9, 2007
 * Time: 8:37:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class BenchmarkTest extends TestCase {
    private static final int ITERATIONS = 100000;

    public void testGameSpeed() {
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
                HandRank rank = hand.getHandRank(board);
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
        System.out.println(ITERATIONS * 1000 / duration + " games/second");
    }

    private static final int TOTAL = 100000;

    public void testEvaluations() {
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
            HandRank rank = hand.getHandRank(board);
        }
        for (int i = 0; i < TOTAL; i++) {
            Hand hand = hands.get(i);
            Board board = boards.get(i);
            HandRank rank = hand.getHandRank(board);
        }
        for (int i = 0; i < TOTAL; i++) {
            Hand hand = hands.get(i);
            Board board = boards.get(i);
            HandRank rank = hand.getHandRank(board);
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println(3 * TOTAL * 1000 / duration + " ranks/second");
    }

    public static void main(String[] args) {
        BenchmarkTest benchmark = new BenchmarkTest();
        benchmark.testEvaluations();
    }
}
