package com.willowtreeapps;

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


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validateTitleIsPresent() {
        WebElement title = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(title != null);
    }

    public void validateClickingFirstPhotoIncreasesTriesCounter() {
        //Wait for page to load
        waitUntilAllImagesLoaded();

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
    private void waitUntilAllImagesLoaded() {
        IntStream.range(0, 5).forEach(this::waitUntilImageLoaded);
    }

    /**
     * Waits until the image with the specified index is loaded.
     *
     * @param imageIndex The index of the image to wait for.
     */
    private void waitUntilImageLoaded(int imageIndex) {
        System.out.println("wait for image: " + imageIndex);

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

            System.out.println("image ready: " + ready);

            return ready;
        };

        new WebDriverWait(driver, 25, 100).until(imageReady);
    }

}
