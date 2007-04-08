package com.sampullara.poker;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: Apr 8, 2007
 * Time: 2:53:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Evaluate {

    public static HandRank omaha(Hand hand, Board board) {
        // In Omaha we must use 2 cards from our hand
        HandRank best = null;
        List<List<Card>> boardSubsets = choose3(board.getCards());
        List<List<Card>> handSubsets = choose2(hand.getCards());
        for (List<Card> boardCards : boardSubsets) {
            for (List<Card> handCards : handSubsets) {
                HandRank hr = Evaluate.defaultEvaluate(handCards, boardCards);
                if (best == null || hr.compareTo(best) > 1) {
                    best = hr;
                }
            }
        }
        return best;
    }

    private static List<List<Card>> choose3(List<Card> cards) {
        // Given a list of cards, give me back a list of the
        // complete list of subsets of size num
        int size = cards.size();
        List<List<Card>> subsets = new ArrayList<List<Card>>();
        for (int i1 = 0; i1 < size - 2; i1++) {
            List<Card> subset1 = new ArrayList<Card>();
            subset1.add(cards.get(i1));
            for (int i2 = i1 + 1; i2 < size - 1; i2++) {
                List<Card> subset2 = new ArrayList<Card>(subset1);
                subset2.add(cards.get(i2));
                for (int i3 = i2 + 1; i3 < size; i3++) {
                    List<Card> subset3 = new ArrayList<Card>(subset2);
                    subset3.add(cards.get(i3));
                    subsets.add(subset3);
                }
            }
        }
        return subsets;
    }

    private static List<List<Card>> choose2(List<Card> cards) {
        // Given a list of cards, give me back a list of the
        // complete list of subsets of size num
        int size = cards.size();
        List<List<Card>> subsets = new ArrayList<List<Card>>();
        for (int i1 = 0; i1 < size - 1; i1++) {
            List<Card> subset = new ArrayList<Card>();
            subset.add(cards.get(i1));
            for (int i2 = i1 + 1; i2 < size; i2++) {
                List<Card> subset1 = new ArrayList<Card>(subset);
                subset1.add(cards.get(i2));
                subsets.add(subset1);
            }
        }
        return subsets;
    }

    public static HandRank holdem(Hand hand, Board board) {
        List<Card> cardsInHand = hand.getCards();
        List<Card> boardCards = board.getCards();
        return defaultEvaluate(cardsInHand, boardCards);
    }

    private static HandRank defaultEvaluate(List<Card> cardsInHand, List<Card> boardCards) {
        // Create a new set of cards with all the cards in it sorted
        List<Card> cards = new ArrayList<Card>(boardCards.size() + cardsInHand.size());
        addSorted(cardsInHand, cards);
        addSorted(boardCards, cards);

        // Look for hands
        return new HandRank(cards);
    }

    static void addSorted(List<Card> cards, List<Card> hand) {
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
