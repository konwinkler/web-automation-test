package com.willowtreeapps;

import static org.testng.Assert.assertNotNull;

/**
 * Verify the title is present.
 */
public class ValidateTitleIsPresent extends WebTestBase {

    @Override
    public void run() {
        assertNotNull(homePage.getTitle(), "Expected title to be present.");
    }

}
