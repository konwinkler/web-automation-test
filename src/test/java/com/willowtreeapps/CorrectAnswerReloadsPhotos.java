package com.willowtreeapps;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies clicking on the correct picture will reload the pictures.
 */
public class CorrectAnswerReloadsPhotos extends WebTestBase {

    @Override
    public void run() {
        // Store initial photos.
        List<String> initialPhotos = homePage.getPhotosSources();

        // Make a correct answer.
        homePage.makeCorrectGuess();

        // Verify displayed photos are different.
        assertThat("Expect photos to change.", homePage.getPhotosSources(), not(initialPhotos));
    }

}
