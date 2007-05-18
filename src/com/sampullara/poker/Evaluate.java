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

    /**
     * This will give you the odds of not losing.  Not sure what to do with ties yet.
     *
     * @param hands
     * @param board
     * @return odds
     */
    public static double[] evaluateWinningOdds(Cards[] hands, Cards board, Cards other) {
        int numhands = hands.length;
        double[] odds = new double[numhands];
        int[] wins = new int[numhands];
        int[] ties = new int[numhands];
        int total = 0;

        // Get a fresh deck
        Cards remaining = new Deck().getCards();

        // Remove the known cards from the deck
        for (Cards hand : hands) {
            for (Card card : hand) {
                remaining.remove(card);
            }
        }
        for (Card card : board) {
            remaining.remove(card);
        }
        for (Card card : other) {
            remaining.remove(card);
        }

        // To what depth are we going to have to search
        if (board.size() == 0) {
            // Need to search 5 cards
            total = search5(hands, board, numhands, wins, ties, remaining);
        } else if (board.size() == 3) {
            // Need to search 2 cards
            total = search2(hands, board, numhands, wins, ties, remaining);
        } else if (board.size() == 4) {
            // Need to search 1 card
            total = search1(hands, board, numhands, wins, ties, remaining);
        } else if (board.size() == 5) {
            // Game is determined
            total = rankHands(hands, board, numhands, wins, ties, total);
        }

        int i = 0;
        for (int win : wins) {
            odds[i++] = ((double) win) / total;
        }


        System.out.println(total);
        return odds;
    }

    private static int search5(Cards[] hands, Cards board, int numhands, int[] wins, int[] ties, Cards remaining) {
        int total = 0;
        for (int i = 0; i < remaining.size() - 4; i++) {
            Card card1 = remaining.get(i);
            board.add(card1);
            for (int j = i + 1; j < remaining.size() - 3; j++) {
                Card card2 = remaining.get(j);
                board.add(card2);
                for (int k = j + 1; k < remaining.size() - 2; k++) {
                    Card card3 = remaining.get(k);
                    board.add(card3);
                    for (int l = k + 1; l < remaining.size() - 1; l++) {
                        Card card4 = remaining.get(l);
                        board.add(card4);
                        for (int m = l + 1; m < remaining.size(); m++) {
                            Card card5 = remaining.get(m);
                            board.add(card5);
                            total = rankHands(hands, board, numhands, wins, ties, total);
                            board.remove(card5);
                        }
                        board.remove(card4);
                    }
                    board.remove(card3);
                }
                board.remove(card2);
            }
            board.remove(card1);
        }
        return total;
    }

    private static int search2(Cards[] hands, Cards board, int numhands, int[] wins, int[] ties, Cards remaining) {
        int total = 0;
        for (int i = 0; i < remaining.size() - 1; i++) {
            Card card1 = remaining.get(i);
            board.add(card1);
            for (int j = i + 1; j < remaining.size(); j++) {
                Card card2 = remaining.get(i);
                board.add(card2);
                total = rankHands(hands, board, numhands, wins, ties, total);
                board.remove(card2);
            }
            board.remove(card1);
        }
        return total;
    }

    private static int rankHands(Cards[] hands, Cards board, int numhands, int[] wins, int[] ties, int total) {
        List<HandRank> ranks = new ArrayList<HandRank>(numhands);
        HandRank highest = null;
        for (Cards hand : hands) {
            HandRank rank = holdem(hand, board);
            if (highest == null) {
                highest = rank;
            } else {
                if (rank.compareTo(highest) > 0) {
                    highest = rank;
                }
            }
            ranks.add(rank);
        }
        int handnum = 0;
        int tied = 0;
        for (HandRank rank : ranks) {
            if (rank.equals(highest)) {
                if (tied > 0) {
                    ties[handnum]++;
                } else {
                    wins[handnum]++;
                }
                tied++;
            }
            handnum++;
        }
        total++;
        return total;
    }

    private static int search1(Cards[] hands, Cards board, int numhands, int[] wins, int[] ties, Cards remaining) {
        int total = 0;
        for (Card card1 : remaining) {
            board.add(card1);
            total = rankHands(hands, board, numhands, wins, ties, total);
            board.remove(card1);
        }
        return total;
    }

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
