/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:07:30 PM
 */
public final class Hand {
    private final List<Card> cards;

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

}
