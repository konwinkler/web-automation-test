package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

/**
 * Verifies making a correct guess increases the streak counter.
 */
public class StreakCounterIncrement extends WebTestBase {

    @Override
    public void run() {
        // Verify stream counter is zero.
        assertEquals(homePage.getStreakCounter(), 0, "Expect streak to be zero at beginning of the game.");

        // Click correct photo.
        homePage.makeCorrectGuess();

        // Verify stream counter is one.
        assertEquals(homePage.getStreakCounter(), 1, "Expect streak to increase after a correct guess.");
    }

}
