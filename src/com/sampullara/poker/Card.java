/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:03:51 PM
 */
public final class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    public int compareTo(Card card) {
        return rank.compareTo(card.rank);
    }

    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;

        private final String[] suitStrings = {"c", "d", "h", "s"};

        public String toString() {
            return suitStrings[this.ordinal()];
        }
    }

    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        private final String[] rankStrings = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

        public String toString() {
            return rankStrings[this.ordinal()];
        }
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return "" + rank + suit;
    }
}
