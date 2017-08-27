package com.willowtreeapps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;

/**
 * Created on 5/23/17.
 */
public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

    HomePage(WebDriver driver) {
        super(driver);
    }

    // Element Section

    private WebElement streak() {
        return driver.findElement(By.className("streak"));
    }

    private WebElement nameToGuess() {
        return driver.findElement(By.id("name"));
    }

    private WebElement photoByName(String name) {
        return driver.findElement(By.xpath(String.format(".//div[./text()='%s']/..", name)));
    }

    // Helper Method Section

    void validateTitleIsPresent() {
        WebElement title = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(title != null);
    }

    void validateClickingFirstPhotoIncreasesTriesCounter() {
        assertEquals(Integer.parseInt(driver.findElement(By.className("attempts")).getText()), 0,
                "Expect to have no tries at beginning of game.");

        driver.findElement(By.className("photo")).click();

        waitUntilAllImagesLoaded();

        assertEquals(Integer.parseInt(driver.findElement(By.className("attempts")).getText()), 1,
        "Expect to have one try after one guess.");
    }

    /**
     * Wait until each of the 5 images is loaded.
     */
    void waitUntilAllImagesLoaded() {
        IntStream.range(0, 5).forEach(this::waitUntilImageLoaded);
    }

    /**
     * Waits until the image with the specified index is loaded.
     *
     * @param imageIndex The index of the image to wait for.
     */
    private void waitUntilImageLoaded(int imageIndex) {
        logger.debug("wait for image: " + imageIndex);

        ExpectedCondition<Boolean> imageReady = input ->
        {
            Boolean ready = false;

            // Get all images.
            List<WebElement> images = driver.findElements(By.tagName("img"));

            // Only if list contains enough images to access the desired index.
            if(images.size() > imageIndex) {
                // Get the image loading status.
                ready = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete",
                        images.get(imageIndex));
            }

            logger.debug("image ready: " + ready);

            return ready;
        };

        new WebDriverWait(driver, 25, 100).until(imageReady);
    }

    /**
     * Returns the current streak value.
     *
     * @return The integer streak.
     */
    int getCurrentStreak() {
        return Integer.parseInt(streak().getText());
    }

    /**
     * Returns the name to guess of the current round.
     *
     * @return The name to guess.
     */
    String getNameToGuess() {
        return nameToGuess().getText();
    }

    /**
     * Clicks the photo identified by the provided employee name.
     *
     * @param name The employee name to identify the photo to click.
     */
    void clickPhotoByName(String name) {
        photoByName(name).click();
        waitUntilAllImagesLoaded();
    }

}
