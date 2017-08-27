package com.willowtreeapps;

import java.util.stream.IntStream;

public class ClickCorrectPhotoTenTimes extends WebTestBase {

    @Override
    public void run() {
        verifyCounters(0, 0, 0);

        String correctName = homePage.getNameToGuess();

        IntStream.range(0, 10).forEach(
             i -> homePage.clickPhotoByName(correctName)
        );

        verifyCounters(1, 1, 1);
    }

}
