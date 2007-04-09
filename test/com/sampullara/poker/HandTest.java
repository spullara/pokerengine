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
 * Cards Tester.
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
        Cards board = new Cards();
        board.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        Cards hand1 = new Cards();
        hand1.add(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        hand1.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        Cards hand2 = new Cards();
        hand2.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        hand2.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        Cards hand3 = new Cards();
        hand3.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        hand3.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        Cards hand4 = new Cards();
        hand4.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand4.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));
        Cards hand5 = new Cards();
        hand5.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        hand5.add(new Card(Card.Rank.SIX, Card.Suit.SPADES));
        Cards hand6 = new Cards();
        hand6.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        hand6.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));

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
