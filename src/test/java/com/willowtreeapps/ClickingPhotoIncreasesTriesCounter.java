package com.willowtreeapps;

public class ClickingPhotoIncreasesTriesCounter extends WebTestBase {

    @Override
    public void run() {
        homePage.validateClickingFirstPhotoIncreasesTriesCounter();
    }

}
