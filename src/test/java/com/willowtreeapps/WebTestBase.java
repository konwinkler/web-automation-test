package com.willowtreeapps;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

@Test
public abstract class WebTestBase {

    private static final Logger logger = LogManager.getLogger(HomePage.class.getName());

    private WebDriver driver;
    HomePage homePage;

    /**
     * Change the prop if you are on Windows or Linux to the corresponding file type
     * The chrome WebDrivers are included on the root of this project, to get the
     * latest versions go to https://sites.google.com/a/chromium.org/chromedriver/downloads
     */
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        homePage = new HomePage(driver);

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.navigate().to("http://www.ericrochester.com/name-game/");
        homePage.waitUntilAllImagesLoaded();
    }

    /**
     * Executes the test.
     *
     * Implemented by test classes.
     */
    @Test
    public abstract void run();

    @AfterClass
    public void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }

    /**
     * Takes a screenshot if the test fails.
     *
     * @param result The result of the test.
     */
    @AfterMethod
    final public void close(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {

                // Take the screenshot.
                TakesScreenshot d = (TakesScreenshot) driver;
                File screenshot = d.getScreenshotAs(OutputType.FILE);

                // Move to screenshot folder and rename with current timestamp.
                File newDestination = new File("screenshots/" + System.currentTimeMillis() + ".png");
                FileUtils.moveFile(screenshot, newDestination);
            }
        } catch (IOException exception) {
            // Print the stack trace if the screenshot could not be takes.
            logger.error("Screenshot could not be taken.", exception);
        }
    }

    /**
     * Verifies all 3 counters display the provided values.
     *
     * @param expectedTries   The expected tries value.
     * @param expectedCorrect The expected correct value.
     * @param expectedStreak  The expected streak value.
     */
    protected void verifyCounters(int expectedTries, int expectedCorrect, int expectedStreak) {
        assertEquals(homePage.getTriesCounter(), expectedTries, "Counter of tries has unexpected value.");
        assertEquals(homePage.getCorrectCounter(), expectedCorrect, "Counter of correct has unexpected value.");
        assertEquals(homePage.getStreakCounter(), expectedStreak, "Counter of streak has unexpected value.");
    }

}
