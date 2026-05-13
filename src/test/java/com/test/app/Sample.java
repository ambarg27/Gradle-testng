package com.test.app;

import com.aventstack.extentreports.Status;
import common.TestBaseClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Sample extends TestBaseClass {

    @Test(groups = "Smoke", priority = 1)
    public void tc00VerifyURL() {

        test = extent.createTest("Verify URL", "Test the TodoMVC link")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("Kunal");

        logger.info("Verify URL");

        webdriver.openURL("https://todomvc.com/examples/react/dist/");

        test.log(Status.INFO, "Open URL");
        logger.info("Open URL");
    }

    @Test(priority = 2, dependsOnMethods = "tc00VerifyURL")
    public void tc01VerifyEnterText() throws InterruptedException {

        test = extent.createTest("Verify Add Todo Items", "")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("Kunal");

        logger.info("Verify Add Todo Items");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        int item_count = 15;

        for (int count = 1; count <= item_count; count++) {
            /* Re-find element each iteration to avoid stale reference in React */
            WebElement elem_new_item = wait.until(
                    ExpectedConditions.elementToBeClickable(IConstants.txtNewTodo));
            elem_new_item.click();
            elem_new_item.sendKeys("Adding a new item " + count + Keys.ENTER);
            test.log(Status.PASS, "New item No. " + count + " is added");
            Thread.sleep(3000);
        }

        test.log(Status.INFO, "Verify Add Todo Items");
        logger.info("Verify Add Todo Items");
    }
}

