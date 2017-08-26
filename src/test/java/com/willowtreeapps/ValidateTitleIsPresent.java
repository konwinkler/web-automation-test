package com.willowtreeapps;

import org.testng.annotations.Test;

public class ValidateTitleIsPresent extends WebTest {

    @Test
    public void test_validate_title_is_present() {
        homePage.validateTitleIsPresent();
    }

}
