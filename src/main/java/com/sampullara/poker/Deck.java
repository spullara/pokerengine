/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import java.util.Collections;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:00:43 PM
 */
public final class Deck {
    private static final int DECK_SIZE = 52;
    private static final Cards deck;

    static {
        deck = new Cards(DECK_SIZE);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    private final Cards cards;

    /**
     * Creates an unshuffled deck of cards.
     */
    public Deck() {
        cards = new Cards(deck);
    }

    public Cards getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        return cards.remove(0);
    }

    public void burn() {
        cards.remove(0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }

}
