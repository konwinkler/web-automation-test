package com.willowtreeapps;

import org.testng.annotations.Test;

public class ClickingPhotoIncreasesTriesCounter extends WebTest {

    @Test
    public void test_clicking_photo_increases_tries_counter() {
        homePage.validateClickingFirstPhotoIncreasesTriesCounter();
    }

}
