package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

public class StreakCounterNotIncrementing extends WebTestBase {

    @Override
    public void run() {
        // Verify stream counter is zero.
        assertEquals(homePage.getCurrentStreak(), 0, "Expect streak to be zero at beginning of the game.");

        // Click incorrect photo.
        homePage.makeWrongGuess();

        // Verify stream counter is not incremented.
        assertEquals(homePage.getCurrentStreak(), 0, "Expect streak to not increase after an incorrect guess.");
    }

}
