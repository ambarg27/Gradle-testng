/*
@author Kunal Soni
*/

package library;

import common.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WebDriver extends TestBaseClass {

    private WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void enterText(By locator, String text) {
        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void clearText(By locator) {
        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
    }

    public void clickOnButton(By locator) {
        getWait(30).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public String getPlaceholder(By locator) {
        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getAttribute("placeholder");
    }

    public void selectValueOnDropDown(By locator, String text) {
        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select selectValue = new Select(driver.findElement(locator));
        selectValue.selectByVisibleText(text);
    }

    public void selectValueOnCheckBox(By chkBoxCollection, By labelText, By getInput, String text) {

        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(chkBoxCollection));

        List<WebElement> chkBoxCollections = driver.findElements(chkBoxCollection);
        for (WebElement webElement : chkBoxCollections) {
            WebElement getLabel = webElement.findElement(labelText);
            WebElement getInput1 = webElement.findElement(getInput);

            if (getLabel.getText().equals(text)) {
                if (!getInput1.isSelected()) {
                    getLabel.click();
                }
                break;
            }
        }
    }

    public void randomClickFromList(By locator) {

        getWait(30).until(ExpectedConditions.visibilityOfElementLocated(locator));

        List<WebElement> itemsInList = driver.findElements(locator);
        int size = itemsInList.size();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, size);
        itemsInList.get(randomNumber).click();
    }

    public static String randomString() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 250) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    public void openURL(String url) {
        driver.get(url);
    }
}
