package com.sampullara.poker;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * HandRank Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04/02/2005</pre>
 */
public class HandRankTest extends TestCase {
    public HandRankTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public static Test suite() {
        return new TestSuite(HandRankTest.class);
    }

    public void testFlush() {
        HandRank handRank = getFlush();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.FLUSH) fail("This should be a flush, instead it is a " + rank);
        HandRank handRank2 = getFlush2();
        HandRank.Rank rank2 = handRank2.getRank();
        if (rank2 != HandRank.Rank.FLUSH) fail("This should be a flush, instead it is a " + rank2);
    }

    private HandRank getFlush() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFlush2() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testStraightFlush() {
        HandRank handRank = getStraightFlush1();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHTFLUSH) fail("This should be a straight flush, instead it is a " + rank);
        handRank = getStraightFlush2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHTFLUSH) fail("This should be a straight flush, instead it is a " + rank);
    }

    private HandRank getStraightFlush1() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getStraightFlush2() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testFourOfAKind() {
        HandRank handRank = getQuads1();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.FOUROFAKIND) fail("This should be four of a kind, instead it is a " + rank);
        handRank = getQuads2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FOUROFAKIND) fail("This should be four of a kind, instead it is a " + rank);
        handRank = getQuads3();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FOUROFAKIND) fail("This should be four of a kind, instead it is a " + rank);
    }

    private HandRank getQuads2() {
        HandRank handRank;
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.DIAMONDS));
        handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getQuads3() {
        HandRank handRank;
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.DIAMONDS));
        handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getQuads1() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testFullHouse() {
        HandRank handRank = getFullHouse1();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.FULLHOUSE) fail("This should be a full house, instead it is a " + rank);
        handRank = getFullHouse2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FULLHOUSE) fail("This should be a full house, instead it is a " + rank);
        handRank = getFullHouse3();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FULLHOUSE) fail("This should be a full house, instead it is a " + rank);
        handRank = getFullHouse4();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FULLHOUSE) fail("This should be a full house, instead it is a " + rank);
        handRank = getFullHouse5();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.FULLHOUSE) fail("This should be a full house, instead it is a " + rank);
    }

    private HandRank getFullHouse2() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFullHouse3() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFullHouse4() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.SPADES));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFullHouse5() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFullHouse1() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testStraight() {
        HandRank handRank = getStraight1();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHT) fail("This should be a straight, instead it is a " + rank);
        handRank = getWheel();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHT) fail("This should be a straight, instead it is a " + rank);
        handRank = getStraight2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHT) fail("This should be a straight, instead it is a " + rank);
        handRank = getStraight3();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHT) fail("This should be a straight, instead it is a " + rank);
        handRank = getStraight4();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.STRAIGHT) fail("This should be a straight, instead it is a " + rank);
    }

    private HandRank getStraight1() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getStraight2() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getStraight3() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getStraight4() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getStraight5() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getWheel() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testThreeOfAKind() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        HandRank.Rank rank = new HandRank(cards).getRank();
        if (rank != HandRank.Rank.THREEOFAKIND) fail("This should be three of a kind, instead it is a " + rank);
        HandRank handRank = getTrips1();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.THREEOFAKIND) fail("This should be three of a kind, instead it is a " + rank);
        handRank = getTrips2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.THREEOFAKIND) fail("This should be three of a kind, instead it is a " + rank);
    }

    private HandRank getTrips2() {
        Cards cards;
        HandRank handRank;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getTrips1() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testTwoPair() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        HandRank.Rank rank = new HandRank(cards).getRank();
        if (rank != HandRank.Rank.TWOPAIR) fail("This should be two pair, instead it is a " + rank);
        HandRank handRank = getTwoPair1();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.TWOPAIR) fail("This should be two pair, instead it is a " + rank);
        handRank = getTwoPair2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.TWOPAIR) fail("This should be two pair, instead it is a " + rank);
        handRank = getTwoPair3();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.TWOPAIR) fail("This should be two pair, instead it is a " + rank);
        handRank = getTwoPair4();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.TWOPAIR) fail("This should be two pair, instead it is a " + rank);
    }

    private HandRank getTwoPair2() {
        Cards cards;
        HandRank handRank;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getTwoPair1() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getTwoPair3() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getTwoPair4() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testPair() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));
        HandRank.Rank rank = new HandRank(cards).getRank();
        if (rank != HandRank.Rank.PAIR) fail("This should be a pair, instead it is a " + rank);
        HandRank handRank = getPair1();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.PAIR) fail("This should be a pair, instead it is a " + rank);
        handRank = getPair2();
        rank = handRank.getRank();
        if (rank != HandRank.Rank.PAIR) fail("This should be a pair, instead it is a " + rank);
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        rank = new HandRank(cards).getRank();
        if (rank != HandRank.Rank.PAIR) fail("This should be a pair, instead it is a " + rank);
    }

    private HandRank getPair2() {
        Cards cards;
        HandRank handRank;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getPair1() {
        Cards cards;
        cards = new Cards(5);
        cards.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.JACK, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testHigh() {
        HandRank handRank = getHigh();
        HandRank.Rank rank = handRank.getRank();
        if (rank != HandRank.Rank.HIGH) fail("This should be high card, instead it is a " + rank);
    }

    private HandRank getHigh() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.SIX, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    private HandRank getFourOfAKind() {
        Cards cards = new Cards(5);
        cards.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.SPADES));
        cards.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
        cards.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        HandRank handRank = new HandRank(cards);
        return handRank;
    }

    public void testComparisons() {
        if (getFullHouse1().compareTo(getFullHouse2()) >= 0) fail("Error");
        if (getHigh().compareTo(getFullHouse2()) >= 0) fail("Error");
        if (getStraight1().compareTo(getFullHouse2()) >= 0) fail("Error");
        if (getStraightFlush1().compareTo(getFullHouse2()) <= 0) fail("Error");
        if (getQuads1().compareTo(getFullHouse2()) <= 0) fail("Error");
        if (getQuads2().compareTo(getFullHouse2()) <= 0) fail("Error");
        if (getQuads1().compareTo(getQuads2()) <= 0) fail("Error");
        if (getTwoPair1().compareTo(getTwoPair2()) >= 0) fail("Error");
        if (getStraight3().compareTo(getStraight4()) != 0) fail("Error");
        if (getStraight4().compareTo(getStraight5()) != 0) fail("Error");
        if (getFullHouse3().compareTo(getFullHouse4()) != 0) fail("Error");

        HandRank[] handSet = new HandRank[]{getStraightFlush1(), getFourOfAKind(), getFullHouse1(), getFlush(), getStraight1(), getTrips1(), getTwoPair1(), getPair1(), getHigh()};
        for (int i = 0; i < handSet.length; i++) {
            for (int j = 0; j < handSet.length; j++) {
                int result = handSet[i].compareTo(handSet[j]);
                if ((result < 0 && i < j) || (result == 0 && i != j) || (result > 0 && i > j))
                    fail("Error: " + result + ", " + handSet[i] + ", " + handSet[j]);
            }
        }
    }

}
