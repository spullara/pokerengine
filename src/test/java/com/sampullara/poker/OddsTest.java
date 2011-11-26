package com.sampullara.poker;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: samp
 * Date: May 18, 2007
 * Time: 1:09:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class OddsTest extends TestCase {

    public void testRiverOdds() {
        // Flush draw vs. made pair on the river
        {
            Cards flushDraw = new Cards("2h3h");
            Cards madePair = new Cards("AdKd");
            Cards board = new Cards("AhThJc6d");
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{flushDraw, madePair}, board, new Cards());
            System.out.println(flushDraw + ": " + odds[0] * 100 + "%");
            System.out.println(madePair + ": " + odds[1] * 100 + "%");
        }

        // Overpair vs underpair on the river
        {
            Cards overpair = new Cards("AdAc");
            Cards underpair = new Cards("2d2h");
            Cards board = new Cards("6c7c8dJh");
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{overpair, underpair}, board, new Cards());
            System.out.println(overpair + ": " + odds[0] * 100 + "%");
            System.out.println(underpair + ": " + odds[1] * 100 + "%");
        }
    }

    public void testTurnOdds() {

        // Flush draw vs. made pair on the turn
        {
            Cards flushDraw = new Cards("2h3h");
            Cards madePair = new Cards("AdKd");
            Cards board = new Cards("AhThJc");
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{flushDraw, madePair}, board, new Cards());
            System.out.println(flushDraw + ": " + odds[0] * 100 + "%");
            System.out.println(madePair + ": " + odds[1] * 100 + "%");
        }

        // Overpair vs underpair on the turn
        {
            Cards overpair = new Cards("AdAc");
            Cards underpair = new Cards("2d2h");
            Cards board = new Cards("6c7c8d");
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{overpair, underpair}, board, new Cards());
            System.out.println(overpair + ": " + odds[0] * 100 + "%");
            System.out.println(underpair + ": " + odds[1] * 100 + "%");
        }
    }

    public void testPreflopOdds() {
        // 23 suited vs AK offsuit, shared suits
        {
            Cards flushDraw = new Cards("2h3h");
            Cards madePair = new Cards("AdKc");
            Cards board = new Cards();
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{flushDraw, madePair}, board, new Cards());
            System.out.println(flushDraw + ": " + odds[0] * 100 + "%");
            System.out.println(madePair + ": " + odds[1] * 100 + "%");
        }

        // AA vs 22, no shared suits
        {
            Cards overpair = new Cards("AsAc");
            Cards underpair = new Cards("2d2h");
            Cards board = new Cards();
            double[] odds = Evaluate.evaluateWinningOdds(new Cards[]{overpair, underpair}, board, new Cards());
            System.out.println(overpair + ": " + odds[0] * 100 + "%");
            System.out.println(underpair + ": " + odds[1] * 100 + "%");
        }
    }
}
