/*
@author Kunal Soni
*/

package com.test.app;

import com.aventstack.extentreports.Status;
import common.TestBaseClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class Sample2 extends TestBaseClass {

    String status;
    @Test(groups = "Smoke")
    public void tc00VerifyURL() throws InterruptedException {

        test = extent.createTest("Verify URL", "Test the google link")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("TestUser");
        logger.info("Verify URL");
        webdriver.openURL("https://www.jivrus.com/resources/articles/technical/how-to-open-browser-console-log");
        webdriver.openURL("https://lambdatest.github.io/sample-todo-app/");
        test.log(Status.INFO, "Open URL");
        logger.info("Open URL");
        Thread.sleep(5000);
    }

    @Test
    public void tc01VerifyEnterText() {
try {
    

        test = extent.createTest("Sample Test 2", "")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("Kunal");

        test.log(Status.PASS, "URL is opened");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        test.log(Status.PASS, "Wait created");

        By textField = By.id("sampletodotext");

        WebElement addText = driver.findElement(textField);

        int item_count = 5;

        for (int i = 1; i <= item_count; i++) {
            addText.click();
            addText.sendKeys("Adding a new item " + i + Keys.ENTER);
            test.log(Status.PASS, "New item No. " + i + " is added");
        }

        WebElement temp_element;

        int totalCount = item_count+5;
        int remaining = totalCount-1;

        for (int i = 1; i <= totalCount; i++, remaining--) {

            String xpath = "(//input[@type='checkbox'])["+i+"]";

            driver.findElement(By.xpath(xpath)).click();
            test.log(Status.PASS, "Item No. " + i + " marked completed");
            By remainingItem = By.className("ng-binding");
            String actualText = driver.findElement(remainingItem).getText();
            String expectedText = remaining+" of "+totalCount+" remaining";

            if (!expectedText.equals(actualText)) {
                test.log(Status.FAIL, "Wrong Text Description");
                status = "failed";
            }

            test.log(Status.PASS, "Item No. " + i + " completed");
        }
    } catch (Exception e) {
        // TODO: handle exception
    }
    }
}

