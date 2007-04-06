/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:07:30 PM
 */
public final class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>(2);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }

    public HandRank getHandRank(Board board) {
        // Create a new set of cards with all the cards in it sorted
        List<Card> hand = new LinkedList<Card>();
        hand.addAll(cards);
        hand.addAll(board.getCards());
        Collections.sort(hand, Collections.reverseOrder());

        // Look for hands
        return new HandRank(hand);
    }
}
