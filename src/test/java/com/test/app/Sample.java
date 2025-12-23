package com.test.app;

import com.aventstack.extentreports.Status;
import common.TestBaseClass;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sample extends TestBaseClass {

    @Test(groups = "Smoke")
    public void tc00VerifyURL() {

        test = extent.createTest("Verify URL", "Test the google link")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("Kunal");

        logger.info("Verify URL");

        webdriver.openURL("https://www.jivrus.com/resources/articles/technical/how-to-open-browser-console-log");

        webdriver.openURL("https://www.google.com");

        test.log(Status.INFO, "Open URL");
        logger.info("Open URL");
    }

    @Test
    public void tc01VerifyEnterText() {

        test = extent.createTest("Verify Search Box", "")
                .assignCategory("Functional_TestCase")
                .assignCategory("Positive_TestCase")
                .assignAuthor("Kunal");

        logger.info("Verify Search Box");

        try {
            // Wait for Google page to load fully
            Thread.sleep(4000);
            webdriver.enterText(IConstants.txtName, "Automation testing");

            // Wait before clicking the button
            Thread.sleep(4000);
            webdriver.clickOnButton(IConstants.btnGoogleSearch);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test.log(Status.INFO, "Verify Search Box");
        logger.info("Verify Search Box");
    }
}

