package com.willowtreeapps;

import java.util.stream.IntStream;

public class ClickWrongPhotoTenTimes extends WebTestBase {

    @Override
    public void run() {
        verifyCounters(0, 0, 0);

        String wrongName = homePage.getFirstWrongName();

        IntStream.range(0, 10).forEach(
             i -> homePage.clickPhotoByName(wrongName)
        );

        verifyCounters(1, 0, 0);
    }

}
