package com.sampullara.poker;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Apr 8, 2007
 * Time: 3:54:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class OmahaTest extends TestCase {
    public void testFullHouse() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Cards board = new Cards();
        board.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        Cards hand1 = new Cards("AhAdAs2s");
        Cards board1 = new Cards("Ac2h2d3d4d");
        assertTrue(fullHouse.compareTo(Evaluate.omaha(hand1, board1)) == 0);
    }

    public void testQuads() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));

        Cards board = new Cards();
        board.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testStraight() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.add(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Cards board = new Cards();
        board.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testFlush() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.add(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Cards board = new Cards();
        board.add(new Card(Card.Rank.KING, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.FOUR, Card.Suit.SPADES));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testStraightFlush() {
        Cards hand = new Cards();
        hand.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.add(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));

        Cards board = new Cards();
        board.add(new Card(Card.Rank.KING, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        board.add(new Card(Card.Rank.FOUR, Card.Suit.SPADES));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }
}
