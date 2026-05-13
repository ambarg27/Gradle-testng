/*
@author Kunal
*/

package com.test.app;

import com.aventstack.extentreports.Status;
import common.TestBaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Sample2 extends TestBaseClass {

    String status;

    @Test(groups = "Smoke", priority = 1)
    public void tc00VerifyURL() {

        test = extent.createTest("Verify URL", "Test the TodoMVC link")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("TestUser");

        logger.info("Verify URL");

        webdriver.openURL("https://www.jivrus.com/resources/articles/technical/how-to-open-browser-console-log");
        webdriver.openURL("https://lambdatest.github.io/sample-todo-app/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampletodotext")));

        test.log(Status.INFO, "Open URL");
        logger.info("Open URL");
    }

    @Test(priority = 2, dependsOnMethods = "tc00VerifyURL")
    public void tc01VerifyEnterText() {
        try {
            test = extent.createTest("Sample Test 2", "")
                    .assignCategory("Functional_TestCase")
                    .assignCategory("Positive_TestCase")
                    .assignAuthor("Kunal");

            test.log(Status.PASS, "URL is opened");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            By textField = By.id("sampletodotext");

            int item_count = 5;

            for (int i = 1; i <= item_count; i++) {
                /* Re-find element each iteration to avoid stale reference */
                WebElement addText = wait.until(ExpectedConditions.elementToBeClickable(textField));
                addText.click();
                addText.sendKeys("Adding a new item " + i + Keys.ENTER);
                test.log(Status.PASS, "New item No. " + i + " is added");
            }

            int totalCount = item_count + 5;
            int remaining = totalCount - 1;

            for (int i = 1; i <= totalCount; i++, remaining--) {
                String xpath = "(//input[@type='checkbox'])[" + i + "]";
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

                test.log(Status.PASS, "Item No. " + i + " marked completed");

                By remainingItem = By.className("ng-binding");
                String actualText = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(remainingItem)).getText();
                String expectedText = remaining + " of " + totalCount + " remaining";

                if (!expectedText.equals(actualText)) {
                    test.log(Status.FAIL, "Wrong Text Description");
                    status = "failed";
                }

                test.log(Status.PASS, "Item No. " + i + " completed");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Exception Occurred: " + e.getMessage());
            status = "failed";
        }
    }
}
