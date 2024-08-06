package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class ApplicationStorePage {

  private static final String SEARCH_FIELD = "//Document[@Name='Shift Apps Directory']//Edit";
  private static final String ACCOUNT_NAME_FIELD = "Account name";
  private static final String SAVE_BUTTON = "Save";
  private final DriverWrapper appDriver;

  public ApplicationStorePage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  public void installApp(String appName, String accName) {
    selectSearchField();
    clickToSearchField();
    inputTextToSearchField(appName);
    clickToSearchField();
    clickAppIcon(appName);
    inputTextToAccountNameField(accName);
    clickToSaveButton();
  }

  @Step
  private void selectSearchField() {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).isSelected();
  }

  @Step
  private void inputTextToSearchField(String text) {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).sendKeys(text);
  }

  @Step
  private void clickToSearchField() {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).click();
  }

  @Step
  private void inputTextToAccountNameField(String text) {
    appDriver.waitForElement(By.name(ACCOUNT_NAME_FIELD)).sendKeys(text);
  }

  @Step
  private void clickToSaveButton() {
    appDriver.waitForElement(By.name(SAVE_BUTTON)).click();
  }

  @Step
  private void clickAppIcon(String appName) {
    appDriver.waitForElement(By.name(appName)).click();
  }
}
