package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

/**
 * Verifies making a wrong guess resets the streak counter
 */
public class StreakCounterReset extends WebTestBase {

    @Override
    public void run() {
        // Verify stream counter is zero.
        homePage.makeCorrectGuess();
        assertEquals(homePage.getStreakCounter(), 1, "Expect streak to be one after a correct guess.");

        // Click incorrect photo.
        homePage.makeWrongGuess();

        // Verify stream counter is not incremented.
        assertEquals(homePage.getStreakCounter(), 0, "Expect streak to reset after an incorrect guess.");
    }

}
