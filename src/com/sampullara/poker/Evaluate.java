package com.sampullara.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Apr 8, 2007
 * Time: 2:53:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Evaluate {

    public static HandRank omaha(Cards hand, Cards board) {
        // In Omaha we must use 2 cards from our hand
        HandRank best = null;
        List<Cards> boardSubsets = choose3(board);
        List<Cards> handSubsets = choose2(hand);
        for (Cards boardCards : boardSubsets) {
            for (Cards handCards : handSubsets) {
                HandRank hr = Evaluate.defaultEvaluate(handCards, boardCards);
                if (best == null || hr.compareTo(best) > 1) {
                    best = hr;
                }
            }
        }
        return best;
    }

    private static List<Cards> choose3(Cards cards) {
        // Given a list of cards, give me back a list of the
        // complete list of subsets of size num
        int size = cards.size();
        List<Cards> subsets = new ArrayList<Cards>();
        for (int i1 = 0; i1 < size - 2; i1++) {
            Cards subset1 = new Cards();
            subset1.add(cards.get(i1));
            for (int i2 = i1 + 1; i2 < size - 1; i2++) {
                Cards subset2 = new Cards(subset1);
                subset2.add(cards.get(i2));
                for (int i3 = i2 + 1; i3 < size; i3++) {
                    Cards subset3 = new Cards(subset2);
                    subset3.add(cards.get(i3));
                    subsets.add(subset3);
                }
            }
        }
        return subsets;
    }

    private static List<Cards> choose2(Cards cards) {
        // Given a list of cards, give me back a list of the
        // complete list of subsets of size num
        int size = cards.size();
        List<Cards> subsets = new ArrayList<Cards>();
        for (int i1 = 0; i1 < size - 1; i1++) {
            Cards subset = new Cards();
            subset.add(cards.get(i1));
            for (int i2 = i1 + 1; i2 < size; i2++) {
                Cards subset1 = new Cards(subset);
                subset1.add(cards.get(i2));
                subsets.add(subset1);
            }
        }
        return subsets;
    }

    public static HandRank holdem(Cards hand, Cards board) {
        return defaultEvaluate(hand, board);
    }

    private static HandRank defaultEvaluate(Cards cardsInHand, Cards boardCards) {
        // Create a new set of cards with all the cards in it sorted
        Cards cards = new Cards(boardCards.size() + cardsInHand.size());
        addSorted(cardsInHand, cards);
        addSorted(boardCards, cards);

        // Look for hands
        return new HandRank(cards);
    }

    static void addSorted(Cards cards, Cards hand) {
        OUTER:
        for (Card card : cards) {
            for (int i = 0; i < hand.size(); i++) {
                if (hand.get(i).compareTo(card) < 0) {
                    hand.add(i, card);
                    continue OUTER;
                }
            }
            hand.add(card);
        }
    }
}
