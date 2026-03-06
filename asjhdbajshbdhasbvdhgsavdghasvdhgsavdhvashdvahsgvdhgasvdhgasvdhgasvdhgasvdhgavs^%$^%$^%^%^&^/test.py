
from appium import webdriver
from appium.options.android import UiAutomator2Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait, Select
from selenium.webdriver.support import expected_conditions as EC
import time, requests, re, os, traceback
try:
    from condition import Condition, ResolvedCondition, ConcatenationOperator
except Exception as e:
    pass
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains

options = UiAutomator2Options()
options.set_capability("platformName", "android")
options.browser_name = "Chrome"

driver = webdriver.Remote("http://localhost:4723", options=options)
driver.get("https://kaneai-playground.lambdatest.io/")
try:

    actions = ActionChains(driver)
    def get_element(driver,locators):
        driver.implicitly_wait(6)
        if isinstance(locators[0], str):
            for locator in locators:
                try:
                    element = driver.find_element(By.XPATH, locator)
                    if element.is_displayed() and element.is_enabled():
                        return element
                except:
                    continue
        else:
            for locator in locators:
                by_method = By.XPATH if str(locator['isXPath']).lower() == "true" else By.CSS_SELECTOR
                try:
                    element = driver.find_element(by_method, locator['selector'])
                    if element.is_displayed() and element.is_enabled():
                        return element
                except:
                    continue
        return None
    driver.implicitly_wait(6)

    # Step - 1 : Click search bar
    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()

    driver.quit()
except Exception as e:
    driver.quit()
