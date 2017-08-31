package com.willowtreeapps;

import java.util.stream.IntStream;

/**
 * Verifies a wrong photo can be clicked ten times.
 */
public class TenTimesWrongPhoto extends WebTestBase {

    @Override
    public void run() {
        verifyCounters(0, 0, 0);

        // Click the wrong photo ten times.
        String wrongName = homePage.getFirstWrongName();
        IntStream.range(0, 10).forEach(
             i -> homePage.clickPhotoByName(wrongName)
        );

        verifyCounters(1, 0, 0);
    }

}
