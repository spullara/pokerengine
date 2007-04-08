/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Hand Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04/02/2005</pre>
 */
public class HandTest extends TestCase
{
    public HandTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetHandRank() throws Exception {
        Board board = new Board();
        board.addCard(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        Hand hand1 = new Hand();
        hand1.addCard(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand1.addCard(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        Hand hand2 = new Hand();
        hand2.addCard(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        hand2.addCard(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        Hand hand3 = new Hand();
        hand3.addCard(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        hand3.addCard(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        Hand hand4 = new Hand();
        hand4.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand4.addCard(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        Hand hand5 = new Hand();
        hand5.addCard(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        hand5.addCard(new Card(Card.Rank.SIX, Card.Suit.SPADES));
        Hand hand6 = new Hand();
        hand6.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        hand6.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));

        SortedSet<HandRank> handranks = new TreeSet<HandRank>();
        handranks.add(Evaluate.holdem(hand1, board));
        handranks.add(Evaluate.holdem(hand2, board));
        handranks.add(Evaluate.holdem(hand3, board));
        handranks.add(Evaluate.holdem(hand4, board));
        handranks.add(Evaluate.holdem(hand5, board));
        handranks.add(Evaluate.holdem(hand6, board));
    }

    public static Test suite() {
        return new TestSuite(HandTest.class);
    }
}
