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

    private WebElement attempts() {
        return stats().findElement(By.className("attempts"));
    }

    private WebElement correct() {
        return stats().findElement(By.className("correct"));
    }

    private WebElement nameToGuess() {
        return driver.findElement(By.id("name"));
    }

    private List<WebElement> photos() {
        return driver.findElements(By.className("photo"));
    }

    private WebElement photoByName(String name) {
        return driver.findElement(By.xpath(String.format(".//div[./text()='%s']/..", name)));
    }

    private WebElement stats() {
        return driver.findElement(By.id("stats"));
    }

    private WebElement streak() {
        return stats().findElement(By.className("streak"));
    }

    private WebElement title() {
        return driver.findElement(By.cssSelector("h1"));
    }

    // Helper Method Section

    /**
     * Clicks on the first photo.
     */
    void clickFirstPhoto() {
        photos().get(0).click();
    }

    /**
     * Clicks the photo identified by the provided employee name.
     *
     * @param name The employee name to identify the photo to click.
     */
    private void clickPhotoByName(String name) {
        photoByName(name).click();
    }

    /**
     * Returns all names associated with an unselected photo.
     *
     * @return All names associated with an unselected photo.
     */
    private List<String> getAllUnselectedPhotoNames() {
        return photos().stream()                                                       // Traverse through all photos
                .filter(photo -> {                                                     // Filter for unselected photos
                    String classes  = photo.getAttribute("class");                     // Inspect the photo's classes
                    return !classes.contains("wrong") && !classes.contains("correct"); // Has not class wrong/correct
                })
                .map(photo -> photo.findElement(By.className("name")))                 // Access name element of photo
                .map(WebElement::getText)                                              // Get the text of the name
                .collect(Collectors.toList());                                         // Return as a list
    }

    /**
     * Returns the displayed value of correct guesses.
     *
     * @return The correct guesses as int.
     */
    int getCorrectCounter() {
        return Integer.valueOf(correct().getText());
    }

    /**
     * Returns the name to guess of the current round.
     *
     * @return The name to guess.
     */
    private String getNameToGuess() {
        return nameToGuess().getText();
    }

    /**
     * Returns the current streak value.
     *
     * @return The integer streak.
     */
    int getStreakCounter() {
        return Integer.parseInt(streak().getText());
    }

    /**
     * Returns the title of the page.
     *
     * @return The title of the page.
     */
    String getTitle() {
        return title().getText();
    }

    /**
     * Returns the displayed tries.
     *
     * @return Displayed tries.
     */
    int getTriesCounter() {
        return Integer.parseInt(attempts().getText());
    }

    /**
     * Clicks the correct photo.
     */
    void makeCorrectGuess() {
        String nameToGuess = getNameToGuess();
        clickPhotoByName(nameToGuess);
        sleep(4500); //TODO: Replace with green background disappears.
        waitUntilAllImagesLoaded();
    }

    /**
     * Clicks on a wrong photo.
     */
    void makeWrongGuess() {
        String nameToGuess = getNameToGuess();
        List<String> unselectedPhotoNames = getAllUnselectedPhotoNames();

        // Find the first name of the photo which is not the name to guess.
        String firstWrongName = unselectedPhotoNames.stream()
                .filter(name -> !name.equals(nameToGuess))
                .findFirst()
                .orElse(null);

        clickPhotoByName(firstWrongName);
        waitUntilAllImagesLoaded();
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

}
