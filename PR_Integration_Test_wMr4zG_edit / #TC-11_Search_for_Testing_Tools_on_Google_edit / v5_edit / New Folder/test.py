
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait,Select
from selenium.webdriver.support import expected_conditions as EC
import time,requests,re,os, traceback
try:
    from condition import Condition, ResolvedCondition, ConcatenationOperator
except Exception as e:
    pass
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from lambdatest_selenium_driver import smartui_snapshot
options = webdriver.ChromeOptions()
driver = webdriver.Chrome(options=options)
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

    # Step - 1 : open https://kaneai-playground.lambdatest.io/
    driver.get("https://kaneai-playground.lambdatest.io/")
    driver.implicitly_wait(6)

    # Step - 2 : Click on the Enable Notification label in the notification toggle section
    element_locators = ['.card > div:nth-child(1) > div:nth-child(1) > h3:nth-child(1)', '.card > div:nth-child(1) > div:nth-child(1) > h3:nth-child(1)', "//h3[text()='Enable Notification']", "//h3[contains(text(),'Enable Notification')]", "//div[contains(@class,'card')]/div[1]/div[1]/h3[1]", "//div[contains(@class,'card')]/div[1]/div[1]/h3[1]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 3 : Click on Step 2 label in the left side vertical stepper
    element_locators = ["//ul[@id='steps']/li[2]/div[2]/div[1]", '#steps > li:nth-child(2) > div:nth-child(2) > div:nth-child(1)', '#steps > li:nth-child(2) > div:nth-child(2) > div:nth-child(1)', "//div[text()='Step 2']", "//div[contains(text(),'Step 2')]", "//ul[contains(@class,'steps')]/li[2]/div[2]/div[1]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 4 : Click on the Enable Notification heading
    element_locators = ['.card > div:nth-child(1) > div:nth-child(1) > h3:nth-child(1)', '.card > div:nth-child(1) > div:nth-child(1) > h3:nth-child(1)', "//h3[text()='Enable Notification']", "//h3[contains(text(),'Enable Notification')]", "//div[contains(@class,'card')]/div[1]/div[1]/h3[1]", "//div[contains(@class,'card')]/div[1]/div[1]/h3[1]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 5 : Click on the toggle switch knob in Enable Notification card
    element_locators = ["//div[@id='switch']/div[1]", "//div[@id='switch']/div[1]", '#switch > div', '#switch > div:nth-child(1)', '.knob', '.switch > div', "//div[contains(@class,'knob')]", "//div[contains(@class,'switch')]/div[1]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 6 : Click on the Enable Notification toggle switch
    element_locators = ["//li[@id='env-firefox']", '#env-firefox', '#env-chrome + li', '.menu > ul:nth-child(2) > li:nth-child(2)', "//li[text()='Firefox']", 'li:has(+ #safari)', "//li[contains(text(),'Firefox')]", "//div[contains(@class,'menu')]/ul[1]/li[2]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 7 : Click on the 'OK' button in unsupported browser popup
    element_locators = ["//div[@id='validationPopup']/div[1]/button[1]", "//div[@id='validationPopup']/div[1]/button[1]", '#popupMessage + button', '#validationPopup > div:nth-child(1) > button:nth-child(3)', '#validationPopup > div:nth-child(1) > button:nth-child(3)', "//button[text()='OK']", "//button[contains(text(),'OK')]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()

    driver.quit()
except Exception as e:
    driver.quit()
