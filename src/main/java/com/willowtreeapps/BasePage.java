package com.willowtreeapps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Base class for web tests.
 *
 * WebDriver is not exposed to subclasses.
 */
class BasePage {

    final private WebDriver driver;

    /**
     * Constructor.
     *
     * @param driver The driver to control the browser.
     */
    BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Find a web element.
     *
     * @param byLocator The locator to find the element.
     * @return The web element found by the locator.
     */
    WebElement findElement(By byLocator) {
        return driver.findElement(byLocator);
    }

    /**
     * Find a list of web element.
     *
     * @param byLocator The locator to find the elements.
     * @return The web elements found by the locator.
     */
    List<WebElement> findElements(By byLocator) {
        return driver.findElements(byLocator);
    }

    /**
     * Wait for explicit waiting.
     *
     * @return The default explicit wait timer.
     */
    WebDriverWait getWait() {
        return new WebDriverWait(driver, 25, 100);
    }

}
