/*
* Copyright (c) 2005 Your Corporation. All Rights Reserved.
*/
package com.sampullara.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * User: sam
 * Date: Apr 2, 2005
 * Time: 4:26:31 PM
 */
public final class HandRank implements Comparable<HandRank> {
    private static final int NUM_SUITS = Card.Suit.values().length;
    private static final int NUM_RANKS = Card.Rank.values().length;

    private final List<Card> hand;
    private final List<Card> fiveCardHand;
    private Rank rank = Rank.HIGH;

    public String toString() {
        return fiveCardHand + ": " + rank.name();
    }

    /**
     * Used to sort handranks.  First check to see which has a higher absolute handrank, then if equal, determine which
     * hand is better.
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


    /**
     * Before calling this method sort the hand from highest to lowest rank.  If you do not do this you will not get interesting results.
     *
     * @param hand The hand must be sorted from highest to lowest rank.
     */
    public HandRank(List<Card> hand) {
        this.hand = hand;
        fiveCardHand = new ArrayList<Card>(5);
        evaluate();
    }

    public Rank getRank() {
        return rank;
    }

    private List<Card> getHand() {
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
        for (int i = ranks.length - 1; i >= 0; i--) {
            Card[] value = ranks[i];
            if (value == null) continue;
            Card.Rank key = RANKS[i];
            int size = numRanks[i];
            if (size >= 4) {
                rank = Rank.FOUROFAKIND;
                // Get the five card hand
                for (int j = 0; j < value.length; j++)
                    fiveCardHand.add(value[j]);
                for (Card card : hand) {
                    if (card.getRank() != key) {
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
        for (int i = ranks.length - 1; i >= 0; i--) {
            Card[] value = ranks[i];
            if (value == null) continue;
            if (numRanks[i] == 3) {
                tripRank = RANKS[i];
                break;
            }
        }
        // Remove this trip and find the biggest match for the house
        final int tripOrd = tripRank.ordinal();
        for (int j = 0; j < numRanks[tripOrd]; j++)
            fiveCardHand.add(ranks[tripOrd][j]);
        ranks[tripOrd] = null;
        Card.Rank pairRank = null;
        // Find the next biggest pair
        for (int i = ranks.length - 1; i >= 0; i--) {
            Card[] value = ranks[i];
            if (value == null) continue;
            if (numRanks[i] >= 2) {
                pairRank = RANKS[i];
                break;
            }
        }
        Card[] pairCards = ranks[pairRank.ordinal()];
        // Just add the first two cards from the match
        for (int i = 0; i < 2; i++) {
            fiveCardHand.add(pairCards[i]);
        }
    }

    private void foundPair() {
        rank = Rank.PAIR;
        Card.Rank firstPair = null;
        for (int i = 0; i < ranks.length; i++) {
            Card[] value = ranks[i];
            if (value == null) continue;
            if (numRanks[i] == 2) {
                firstPair = RANKS[i];
                break;
            }
        }
        final int firstOrd = firstPair.ordinal();
        for (int i = 0; i < numRanks[firstOrd]; i++)
            fiveCardHand.add(ranks[firstOrd][i]);
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
            // Purposeful optimization
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
        // Make sure you add the Ace in for the wheel
        if (fiveCardHand.size() == 4) {
            fiveCardHand.add(hand.get(0));
        }
        rank = Rank.STRAIGHT;
    }

    private void foundTrips() {
        rank = Rank.THREEOFAKIND;
        Card.Rank tripRank = null;
        // Find the biggest trips
        for (int i = 0; i < ranks.length; i++) {
            Card[] value = ranks[i];
            if (value == null) continue;
            if (numRanks[i] == 3) {
                tripRank = RANKS[i];
                for (int j = 0; j < numRanks[i]; j++)
                    fiveCardHand.add(ranks[i][j]);
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
        Card.Rank firstPair = null;
        Card.Rank secondPair = null;
        int firstOrd = -1;
        int secondOrd = -1;
        for (int i = ranks.length - 1; i >= 0; i--) {
            Card[] value = ranks[i];
            if (value == null) continue;
            if (numRanks[i] == 2) {
                Card.Rank rank = RANKS[i];
                if (firstPair == null) {
                    firstPair = rank;
                    firstOrd = firstPair.ordinal();
                } else {
                    secondPair = rank;
                    secondOrd = secondPair.ordinal();
                    break;
                }
            }
        }
        for (int i = 0; i < numRanks[firstOrd]; i++)
            fiveCardHand.add(ranks[firstOrd][i]);
        for (int i = 0; i < numRanks[secondOrd]; i++)
            fiveCardHand.add(ranks[secondOrd][i]);
        for (Card card : hand) {
            if (card.getRank() != firstPair && card.getRank() != secondPair) {
                fiveCardHand.add(card);
                break;
            }
        }
    }

    private boolean isStraightFlushOrFlush() {
        for (int i = 0; i < suits.length; i++) {
            Card[] cards = suits[i];
            if (cards == null) continue;
            if (numSuits[i] >= 5) {
                int[] inarow = {1};
                Card firstCard = checkStraight(cards, numSuits[i], inarow);
                if (inarow[0] == 5) {
                    boolean add = false;
                    for (int j = 0; j < numSuits[i]; j++) {
                        Card card = suits[i][j];
                        // Purposeful optimization
                        if (card == firstCard) {
                            add = true;
                        }
                        if (add) {
                            fiveCardHand.add(card);
                        }
                        if (fiveCardHand.size() == 5) break;
                    }
                    if (fiveCardHand.size() == 4) {
                        fiveCardHand.add(suits[i][0]);
                    }
                    rank = Rank.STRAIGHTFLUSH;
                } else {
                    // At least a flush
                    for (int j = 0; j < 5; j++) {
                        fiveCardHand.add(cards[j]);
                        rank = Rank.FLUSH;
                    }
                }
                return true;
            }
        }
        return false;
    }

    // This would have been a good place to use a closure.  Make sure that this
    // method changes in sync with the other version
    private static Card checkStraight(List<Card> hand, int[] inarow) {
        // This is the value of the straight
        Card firstCard = null;
        // Keep track of the last card so we can skip over pairs and determine continuity
        Card lastCard = null;
        // Check to see if there is a straight as well
        int num = hand.size();
        for (int i = 0; i < num; i++) {
            Card card = hand.get(i);
            if (lastCard != null) {
                int lastOrd = lastCard.getRank().ordinal();
                int cardOrd = card.getRank().ordinal();
                if (lastOrd - cardOrd == 1) {
                    inarow[0]++;
                    if (inarow[0] == 4 && card.getRank() == Card.Rank.TWO && hand.get(0).getRank() == Card.Rank.ACE) {
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
            if (num - i + inarow[0] < 5) return null;
        }
        return null;
    }

    private static Card checkStraight(Card[] cards, int num, int[] inarow) {
        // This is the value of the straight
        Card firstCard = null;
        // Keep track of the last card so we can skip over pairs and determine continuity
        Card lastCard = null;
        // Check to see if there is a straight as well
        for (int i = 0; i < num; i++) {
            Card card = cards[i];
            if (lastCard != null) {
                int lastOrd = lastCard.getRank().ordinal();
                int cardOrd = card.getRank().ordinal();
                if (lastOrd - cardOrd == 1) {
                    inarow[0]++;
                    if (inarow[0] == 4 && card.getRank() == Card.Rank.TWO && cards[0].getRank() == Card.Rank.ACE) {
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
            if (num - i + inarow[0] < 5) return null;
        }
        return null;
    }

    private final Card[][] ranks = new Card[NUM_RANKS][];
    private final int[] numRanks = new int[NUM_RANKS];
    private final Card[][] suits = new Card[NUM_SUITS][];
    private final int[] numSuits = new int[NUM_SUITS];

    private static final Card.Rank[] RANKS = new Card.Rank[NUM_RANKS];
    private static final Card.Suit[] SUITS = new Card.Suit[NUM_SUITS];

    static {
        int i = 0;
        for (Card.Rank rank : Card.Rank.values()) {
            RANKS[i++] = rank;
        }
        i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            SUITS[i++] = suit;
        }
    }

    private void assertCard(Card card) {
        int cardRank = card.getRank().ordinal();
        Card[] rankList = ranks[cardRank];
        if (rankList == null) {
            rankList = new Card[NUM_SUITS];
            ranks[cardRank] = rankList;
        }
        rankList[numRanks[cardRank]++] = card;
        int cardSuit = card.getSuit().ordinal();
        Card[] suitList = suits[cardSuit];
        if (suitList == null) {
            suitList = new Card[NUM_RANKS];
            suits[cardSuit] = suitList;
        }
        suitList[numSuits[cardSuit]++] = card;
    }

}
