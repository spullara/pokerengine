/*
* Copyright (c) 2005 Your Corporation. All Rights Reserved.
*/
package com.sampullara.poker;

import java.util.*;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:26:31 PM
 */
public final class HandRank implements Comparable<HandRank> {
    private final List<Card> hand;
    private static final int NUM_SUITS = Card.Suit.values().length;
    private static final int NUM_RANKS = Card.Rank.values().length;

    public String toString() {
        return fiveCardHand + ": " + rank.name();
    }

    /**
     * Used to sort handranks.  First check to see which has a higher absolute handrank, then if equal, determine which
     * hand is better.
     *
     * @param handRank
     * @return
     */
    public int compareTo(HandRank handRank) {
        int compare = getRank().compareTo(handRank.getRank());
        if (compare != 0) {
            return compare;
        }
        List<Card> myHand = getHand();
        List<Card> theirHand = handRank.getHand();
        for (int i = 0; i < 5; i++) {
            if (myHand.size() > i) {
                compare = myHand.get(i).compareTo(theirHand.get(i));
                if (compare != 0) return compare;
            }
        }
        return 0;
    }

    public enum Rank {
        HIGH, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH, FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
    }

    private Rank rank = Rank.HIGH;
    private final List<Card> fiveCardHand;
    private final Map<Card.Rank, List<Card>> ranks;
    private final Map<Card.Suit, List<Card>> suits;

    /**
     * Before calling this method sort the hand from highest to lowest rank.  If you do not do this you will not get interesting results.
     *
     * @param hand The hand must be sorted from highest to lowest rank.
     */
    public HandRank(List<Card> hand) {
        this.hand = hand;
        fiveCardHand = new ArrayList<Card>(5);
        ranks = new HashMap<Card.Rank, List<Card>>(NUM_RANKS * 2);
        suits = new HashMap<Card.Suit, List<Card>>(NUM_SUITS * 2);
        evaluate();
    }

    public Rank getRank() {
        return rank;
    }

    public List<Card> getHand() {
        return fiveCardHand;
    }


    /*
     * Evaluate the hand using the rules of poker.
     *
     */
    private void evaluate() {
        // Bin cards by rank and suit
        for (Card card : hand) {
            assertCard(card);
        }

        // Check for flushes and straight flushes
        if (isStraightFlushOrFlush()) {
            if (rank == Rank.STRAIGHTFLUSH) return;
            // If you have a FLUSH and there are 7 or fewer cards, nothing else can beat it
            if (hand.size() <= 7) return;
        }

        // We'll need this to determine the rest of the hands
        int trips = 0;
        int pairs = 0;

        // Check for 4 of a kind, count trips/pairs
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            List<Card> value = entry.getValue();
            int size = value.size();
            if (size >= 4) {
                rank = Rank.FOUROFAKIND;
                // Get the five card hand
                fiveCardHand.addAll(value);
                for (Card card : hand) {
                    if (card.getRank() != entry.getKey()) {
                        fiveCardHand.add(card);
                        return;
                    }
                }
            }
            if (size == 3) trips++;
            if (size == 2) pairs++;
        }

        // Find full houses
        if ((trips == 1 && pairs >= 1) || (trips > 1)) {
            foundFullHouse();
            return;
        }

        // Now we use the flush from above
        if (rank == Rank.FLUSH) {
            return;
        }

        // Find the straights
        int[] inarow = {1};
        Card firstCard = checkStraight(hand, inarow);
        if (inarow[0] == 5) {
            foundStraight(firstCard);
            return;
        }

        // Trips
        if (trips == 1) {
            foundTrips();
            return;
        }

        // Two of a kind
        if (pairs >= 2) {
            foundTwoPair();
            return;
        }

        // One Pair
        if (pairs == 1) {
            foundPair();
            return;
        }

        // High Card
        for (int i = 0; i < 5; i++) {
            if (hand.size() > i) fiveCardHand.add(hand.get(i));
        }
    }

    private void foundFullHouse() {
        rank = Rank.FULLHOUSE;
        Card.Rank tripRank = null;
        // Find the biggest trips
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            if (entry.getValue().size() == 3 && (tripRank == null || tripRank.ordinal() < entry.getKey().ordinal())) {
                tripRank = entry.getKey();
            }
        }
        // Remove this trip and find the biggest match for the house
        fiveCardHand.addAll(ranks.remove(tripRank));
        Card.Rank pairRank = null;
        // Find the next biggest pair
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            Card.Rank rank = entry.getKey();
            if (entry.getValue().size() >= 2 && (pairRank == null || pairRank.ordinal() < rank.ordinal())) {
                pairRank = rank;
            }
        }
        List<Card> pairCards = ranks.get(pairRank);
        // Just add the first two cards from the match
        for (int i = 0; i < 2; i++) {
            fiveCardHand.add(pairCards.get(i));
        }
    }

    private void foundPair() {
        rank = Rank.PAIR;
        Card.Rank firstPair = null;
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            if (entry.getValue().size() == 2) {
                firstPair = entry.getKey();
                break;
            }
        }
        fiveCardHand.addAll(ranks.get(firstPair));
        int i = 0;
        for (Card card : hand) {
            if (card.getRank() != firstPair) {
                fiveCardHand.add(card);
                if (++i == 3) break;
            }
        }
    }

    private void foundStraight(Card firstCard) {
        boolean add = false;
        Card last = null;
        for (Card card : hand) {
            if (card == firstCard) {
                add = true;
            }
            if (add) {
                if (last == null || last.getRank() != card.getRank()) {
                    fiveCardHand.add(card);
                    if (fiveCardHand.size() == 5) break;
                    last = card;
                }
            }
        }
        rank = Rank.STRAIGHT;
    }

    private void foundTrips() {
        rank = Rank.THREEOFAKIND;
        Card.Rank tripRank = null;
        // Find the biggest trips
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            if (entry.getValue().size() == 3) {
                tripRank = entry.getKey();
                fiveCardHand.addAll(entry.getValue());
                break;
            }
        }
        int i = 0;
        for (Card card : hand) {
            if (card.getRank() != tripRank) {
                fiveCardHand.add(card);
                i++;
            }
            if (i == 2) break;
        }
    }

    private void foundTwoPair() {
        rank = Rank.TWOPAIR;
        Card.Rank firstPairRank = null;
        Card.Rank secondPair = null;
        for (Map.Entry<Card.Rank, List<Card>> entry : ranks.entrySet()) {
            if (entry.getValue().size() == 2) {
                Card.Rank rank = entry.getKey();
                int rankOrd = rank.ordinal();
                int secondOrd = secondPair == null ? 0 : secondPair.ordinal();
                if (firstPairRank == null) {
                    firstPairRank = rank;
                } else if (firstPairRank.ordinal() < rankOrd) {
                    if (secondPair == null) {
                        secondPair = firstPairRank;
                    } else if (secondOrd < rankOrd) {
                        secondPair = firstPairRank;
                    }
                    firstPairRank = rank;
                } else if (secondPair == null) {
                    secondPair = rank;
                } else if (secondOrd < rankOrd) {
                    secondPair = rank;
                }
            }
        }
        fiveCardHand.addAll(ranks.get(firstPairRank));
        fiveCardHand.addAll(ranks.get(secondPair));
        for (Card card : hand) {
            if (card.getRank() != firstPairRank && card.getRank() != secondPair) {
                fiveCardHand.add(card);
                break;
            }
        }
    }

    private boolean isStraightFlushOrFlush() {
        for (Map.Entry<Card.Suit, List<Card>> entry : suits.entrySet()) {
            List<Card> cards = entry.getValue();
            if (cards.size() >= 5) {
                int[] inarow = {1};
                Card firstCard = checkStraight(cards, inarow);
                if (inarow[0] == 5) {
                    boolean add = false;
                    for (Card card : cards) {
                        if (card == firstCard) {
                            add = true;
                        }
                        if (add) {
                            fiveCardHand.add(card);
                        }
                        if (fiveCardHand.size() == 5) break;
                    }
                    rank = Rank.STRAIGHTFLUSH;
                } else {
                    // At least a flush
                    for (int i = 0; i < 5; i++) {
                        fiveCardHand.add(cards.get(i));
                        rank = Rank.FLUSH;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static Card checkStraight(List<Card> cards, int[] inarow) {
        // This is the value of the straight
        Card firstCard = null;
        // Keep track of the last card so we can skip over pairs and determine continuity
        Card lastCard = null;
        // Check to see if there is a straight as well
        int size = cards.size();
        for (int i = 0; i < size; i++) {
            Card card = cards.get(i);
            if (lastCard != null) {
                int lastOrd = lastCard.getRank().ordinal();
                int cardOrd = card.getRank().ordinal();
                if (lastOrd - cardOrd == 1) {
                    inarow[0]++;
                    if (inarow[0] == 4 && card.getRank() == Card.Rank.TWO && cards.get(0).getRank() == Card.Rank.ACE) {
                        // Wheel
                        inarow[0]++;
                    }
                } else if (lastOrd - cardOrd != 0) {
                    inarow[0] = 1;
                    firstCard = card;
                }
            } else {
                firstCard = card;
            }
            lastCard = card;

            // Highest straight found
            if (inarow[0] == 5) return firstCard;

            // OPT: If we have a straight or have too few cards remaining, return.
            if (size - i + inarow[0] < 5) return null;
        }
        return null;
    }

    private void assertCard(Card card) {
        List<Card> rankList = ranks.get(card.getRank());
        if (rankList == null) {
            rankList = new LinkedList<Card>();
            ranks.put(card.getRank(), rankList);
        }
        rankList.add(card);
        List<Card> suitList = suits.get(card.getSuit());
        if (suitList == null) {
            suitList = new LinkedList<Card>();
            suits.put(card.getSuit(), suitList);
        }
        suitList.add(card);
    }

}
