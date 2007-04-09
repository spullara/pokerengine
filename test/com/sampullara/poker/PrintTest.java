package com.sampullara.poker;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Mar 16, 2007
 * Time: 6:58:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrintTest extends TestCase {
    public void testDeckPrint() {
        Deck deck = new Deck();
        if (!"2c3c4c5c6c7c8c9cTcJcQcKcAc2d3d4d5d6d7d8d9dTdJdQdKdAd2h3h4h5h6h7h8h9hThJhQhKhAh2s3s4s5s6s7s8s9sTsJsQsKsAs".equals(deck.toString())) {
            fail("Deck not correct: " + deck);
        }
    }

    public void testHandPrint() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        if (!"AcAd".equals(hand.toString())) {
            fail("Not AcAd: " + hand);
        }
        List<Card> cards = hand.getCards();
        Card ac = cards.get(0);
        if (!"Ac".equals(ac.toString())) {
            fail("Not Ac: " + ac);
        }
        Card ad = cards.get(1);
        if (!"Ad".equals(ad.toString())) {
            fail("Not Ad: " + ac);
        }
    }

    public void testBoardPrint() {
        Cards board = new Cards();
        board.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        if (!"AcAd2c".equals(board.toString())) {
            fail("Cards not AcAd2c: " + board);
        }
    }

    public void testHandRankPrint() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        Cards board = new Cards();
        board.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.KING, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        HandRank rank = Evaluate.holdem(hand, board);
        if (!"[Ac, Ad, As, Kc, Kd]: FULLHOUSE".equals(rank.toString())) {
            fail("Not [Ac, Ad, As, Kc, Kd]: FULLHOUSE: " + rank);
        }
    }
}
