package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

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

    private void verifyCounters(int expectedTries, int expectedCorrect, int expectedStreak) {
        assertEquals(homePage.getTriesCounter(), expectedTries, "Counter of tries has unexpected value.");
        assertEquals(homePage.getCorrectCounter(), expectedCorrect, "Counter of correct has unexpected value.");
        assertEquals(homePage.getStreakCounter(), expectedStreak, "Counter of streak has unexpected value.");
    }

}
