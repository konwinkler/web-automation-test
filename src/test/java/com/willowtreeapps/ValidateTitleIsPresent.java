package com.willowtreeapps;

import static org.testng.Assert.assertNotNull;

public class ValidateTitleIsPresent extends WebTestBase {

    @Override
    public void run() {
        assertNotNull(homePage.getTitle(), "Expect title to be present.");
    }

}
