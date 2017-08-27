package com.willowtreeapps;

public class ClickingPhotoIncreasesTriesCounter extends WebTest {

    @Override
    public void run() {
        homePage.validateClickingFirstPhotoIncreasesTriesCounter();
    }

}
