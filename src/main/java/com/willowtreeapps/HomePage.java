package com.willowtreeapps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 5/23/17.
 */
public class HomePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

    HomePage(WebDriver driver) {
        super(driver);
    }

    // Elements Section

    private WebElement streak() {
        return driver.findElement(By.className("streak"));
    }

    private WebElement nameToGuess() {
        return driver.findElement(By.id("name"));
    }

    private WebElement photoByName(String name) {
        return driver.findElement(By.xpath(String.format(".//div[./text()='%s']/..", name)));
    }

    private List<WebElement> photoNames() {
        return driver.findElements(By.className("name"));
    }

    private WebElement title() {
        return driver.findElement(By.cssSelector("h1"));
    }

    private List<WebElement> photos() {
        return driver.findElements(By.className("photo"));
    }

    private WebElement attempts() {
        return driver.findElement(By.className("attempts"));
    }

    // Helper Method Section

    String getTitle() {
        return title().getText();
    }

    /**
     * Clicks on the first photo.
     */
    void clickFirstPhoto() {
        photos().get(0).click();
        waitUntilAllImagesLoaded();
    }

    /**
     * Returns the displayed tries.
     *
     * @return Displayed tries.
     */
    int getTries() {
        return Integer.parseInt(attempts().getText());
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

    /**
     * Clicks on a wrong photo.
     */
    void makeWrongGuess() {
        String nameToGuess = getNameToGuess();
        List<String> allPhotoNames = getAllPhotoNames();

        // Find the first name of the photo which is not the name to guess.
        String firstWrongName = allPhotoNames.stream()
                .filter(name -> !name.equals(nameToGuess))
                .findFirst()
                .orElse(null);

        clickPhotoByName(firstWrongName);
    }

    /**
     * Returns all names associated with a photo.
     *
     * @return All names associated with a photo.
     */
    private List<String> getAllPhotoNames() {
        return photoNames().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
