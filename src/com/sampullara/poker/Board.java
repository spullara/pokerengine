/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:19:41 PM
 */
public final class Board {
    private List<Card> cards;

    public Board() {
        cards = new ArrayList<Card>(5);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }
}
