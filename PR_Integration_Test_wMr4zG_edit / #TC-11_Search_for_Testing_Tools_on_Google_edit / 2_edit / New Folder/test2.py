
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

    class element_to_be_input_and_text(object):
        def __call__(self, driver):
            focused_element = driver.execute_script("return document.activeElement;")
            if focused_element.tag_name == "input" or focused_element.tag_name == "textarea" or focused_element.get_attribute("contenteditable") == "true":
                return focused_element
            else:
                return False
    driver.implicitly_wait(6)

    # Step - 1 : Open https://google.com
    driver.get("https://google.com")
    driver.implicitly_wait(6)

    # Step - 2 : Search for 'testing tools'
    element_locators = ["//textarea[@id='APjFqb' and @name='q']", "//textarea[@name='q' and @title='Search']", "//textarea[@title='Search' and @role='combobox']", '#APjFqb', '[name="q"][title="Search"]', '[title="Search"][aria-label="Search"]', '[title="Search"][role="combobox"]', '[title="Search"]', "//textarea[@title='Search' and @aria-label='Search']", '.gLFyf', "//textarea[starts-with(@title,'Searc')]", "//textarea[contains(@class,'gLFyf')]", "//textarea[contains(@title,'Search')]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    wait = WebDriverWait(driver, 10)
    focused_element = wait.until(element_to_be_input_and_text())
    focused_element = wait.until(EC.element_to_be_clickable(focused_element))
    driver.execute_script("arguments[0].value = '';", focused_element)
    focused_element.send_keys('testing tools')
    focused_element.send_keys(Keys.RETURN)
    driver.implicitly_wait(6)

    # Step - 3 : Click on the clear search text button in the search bar
    element_locators = ['.ExCKkf > svg', '.rzyADb > svg', '.ExCKkf.z1asCe > svg', '.ExCKkf.rzyADb > svg', "//span[contains(@class,'ExCKkf')]/*[local-name()='svg']", "//span[contains(@class,'rzyADb')]/*[local-name()='svg']", "//span[contains(@class,'ExCKkf') and contains(@class,'z1asCe') and contains(@class,'rzyADb')]/*[local-name()='svg']", "//span[contains(@class,'ExCKkf')]/*[local-name()='svg']"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()
    driver.implicitly_wait(6)

    # Step - 4 : Click on the search input field in the top center with red border
    element_locators = ["//textarea[@id='APjFqb' and @name='q']", "//textarea[@name='q' and @role='combobox']", '#APjFqb', '[name="q"][role="combobox"]', '[role="combobox"][aria-label="Search"]', "//textarea[@role='combobox' and @aria-label='Search']", 'textarea.gLFyf', "//textarea[text()='testing tools']", "//textarea[starts-with(@aria-label,'Searc')]", "//textarea[contains(@class,'gLFyf')]"]
    element = get_element(driver,element_locators)

    try:
        actions.move_to_element(element).click().perform()
    except:
        element.click()

    driver.quit()
except Exception as e:
    driver.quit()
