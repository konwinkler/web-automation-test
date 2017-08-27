package com.willowtreeapps;

import static org.testng.Assert.assertEquals;

public class ClickingPhotoIncreasesTriesCounter extends WebTestBase {

    @Override
    public void run() {
        assertEquals(homePage.getTriesCounter(), 0, "Expect to have no tries at beginning of game.");

        homePage.clickFirstPhoto();

        assertEquals(homePage.getTriesCounter(), 1, "Expect to have one try after one guess.");
    }

}
