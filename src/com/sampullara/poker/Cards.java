/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.sampullara.poker;

import java.util.ArrayList;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:07:30 PM
 */
public class Cards extends ArrayList<Card> {
    public Cards(String string) {
        addAll(Card.parse(string));
    }

    public Cards() {
    }

    public Cards(int size) {
        super(size);
    }

    public Cards(Cards deck) {
        super(deck);
    }

    @Override
    public boolean add(Card card) {
        return super.add(card);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this) {
            sb.append(card.toString());
        }
        return sb.toString();
    }
}
