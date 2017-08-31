package com.willowtreeapps;

/**
 * Verifies a series of 10 clicks produce the correct results.
 *
 * This test will make 10 clicks, 5 of them on correct pictures with a maximum streak of 3.
 */
public class ClickTenPhotos extends WebTestBase {

    @Override
    public void run() {
        verifyCounters(0, 0, 0);

        // Click 1: Wrong
        homePage.makeWrongGuess();
        verifyCounters(1, 0, 0);

        // Click 2: Correct
        homePage.makeCorrectGuess();
        verifyCounters(2, 1, 1);

        // Click 3: Correct
        homePage.makeCorrectGuess();
        verifyCounters(3, 2, 2);

        // Click 4: Wrong
        homePage.makeWrongGuess();
        verifyCounters(4, 2, 0);

        // Click 5: Wrong
        homePage.makeWrongGuess();
        verifyCounters(5, 2, 0);

        // Click 6: Correct
        homePage.makeCorrectGuess();
        verifyCounters(6, 3, 1);

        // Click 7: Correct
        homePage.makeCorrectGuess();
        verifyCounters(7, 4, 2);

        // Click 8: Correct
        homePage.makeCorrectGuess();
        verifyCounters(8, 5, 3);

        // Click 9: Wrong
        homePage.makeWrongGuess();
        verifyCounters(9, 5, 0);

        // Click 10: Wrong
        homePage.makeWrongGuess();
        verifyCounters(10, 5, 0);
    }

}
