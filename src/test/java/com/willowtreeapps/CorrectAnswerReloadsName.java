package com.willowtreeapps;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Verifies clicking on the correct picture will reload the name.
 */
public class CorrectAnswerReloadsName extends WebTestBase {

    @Override
    public void run() {
        // Store initial names.
        String initialName = homePage.getNameToGuess();

        // Make a correct answer.
        homePage.makeCorrectGuess();

        // Verify displayed name is different.
        assertThat("Expect 'Who is' name to change.", homePage.getNameToGuess(), not(initialName));
    }

}
