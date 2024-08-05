package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class ApplicationStorePage {

  private static final String SEARCH_FIELD = "//Document[@Name='Shift Apps Directory']//Edit";
  private static final String ACCOUNT_NAME_FIELD = "Account name";
  private static final String SAVE_BUTTON = "Save";
  private final DriverWrapper appDriver;

  public ApplicationStorePage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  public void selectSearchField() {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).isSelected();
  }

  public void inputTextToSearchField(String text) {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).sendKeys(text);
  }

  public void clickToSearchField() {
    appDriver.waitForElement(By.xpath(SEARCH_FIELD)).click();
  }

  public void inputTextToAccountNameField(String text) {
    appDriver.waitForElement(By.name(ACCOUNT_NAME_FIELD)).sendKeys(text);
  }

  public void clickToSaveButton() {
    appDriver.waitForElement(By.name(SAVE_BUTTON)).click();
  }
}
