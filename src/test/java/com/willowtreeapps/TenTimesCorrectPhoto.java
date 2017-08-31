package com.willowtreeapps;

import java.util.stream.IntStream;

/**
 * Verifies the correct photo can be clicked 10 times.
 */
public class TenTimesCorrectPhoto extends WebTestBase {

    @Override
    public void run() {
        verifyCounters(0, 0, 0);

        // CLicks the correct name 10 times.
        String correctName = homePage.getNameToGuess();
        IntStream.range(0, 10).forEach(
             i -> homePage.clickPhotoByName(correctName)
        );
        homePage.waitUntilCorrectSelectionNotDisplayed();

        // Verifies the result.
        verifyCounters(1, 1, 1);
    }

}
