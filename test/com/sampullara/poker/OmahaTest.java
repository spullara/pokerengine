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
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Board board = new Board();
        board.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testQuads() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.HEARTS));

        Board board = new Board();
        board.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        board.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        board.addCard(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testStraight() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Board board = new Board();
        board.addCard(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testFlush() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));

        Board board = new Board();
        board.addCard(new Card(Card.Rank.KING, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.FOUR, Card.Suit.SPADES));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }

    public void testStraightFlush() {
        Hand hand = new Hand();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS));
        hand.addCard(new Card(Card.Rank.FIVE, Card.Suit.SPADES));
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));

        Board board = new Board();
        board.addCard(new Card(Card.Rank.KING, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
        board.addCard(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        board.addCard(new Card(Card.Rank.FOUR, Card.Suit.SPADES));

        HandRank fullHouse = Evaluate.omaha(hand, board);
        System.out.println(fullHouse);
    }
}
