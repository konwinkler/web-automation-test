package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

public class StreakCounterIncrementing extends WebTest {

    @Override
    public void run() {
        // Verify stream counter is zero.
        assertEquals(homePage.getCurrentStreak(), 0, "Expect streak to be zero at beginning of the game.");

        // Click correct photo.
        String nameToGuess = homePage.getNameToGuess();
        homePage.clickPhotoByName(nameToGuess);

        // Verify stream counter is one.
        assertEquals(homePage.getCurrentStreak(), 1, "Expect streak to increase after a correct guess.");
    }

}
