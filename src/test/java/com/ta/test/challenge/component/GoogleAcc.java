package com.ta.test.challenge.component;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class GoogleAcc {

  private static final String LOGIN_FIELD = "//*[@AutomationId='identifierId']";
  private static final String NEXT_BUTTON = "Next";
  private static final String PASSWORD_FIELD = "Enter your password";
  private static final String CONTINUE_BUTTON = "Continue";
  private static final String SELECT_ALL_FIELD = "//*[@AutomationId='i1']";
  private final DriverWrapper appDriver;

  public GoogleAcc(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  public void clickToLoginField() {
    appDriver.waitForElement(By.xpath(LOGIN_FIELD)).click();
  }

  public void inputTextToLoginField(String text) {
    appDriver.waitForElement(By.xpath(LOGIN_FIELD)).sendKeys(text);
  }

  public void clickToNextButton() {
    appDriver.waitForElement(By.name(NEXT_BUTTON)).click();
  }

  public void clickToPasswordField() {
    appDriver.waitForElement(By.name(PASSWORD_FIELD)).click();
  }

  public void inputTextToPasswordField(String text) {
    appDriver.waitForElement(By.name(PASSWORD_FIELD)).sendKeys(text);
  }

  public void clickContinueButton() {
    appDriver.waitForElement(By.name(CONTINUE_BUTTON)).click();
  }

  public void clickSelectAllField() {
    appDriver.waitForElement(By.xpath(SELECT_ALL_FIELD)).click();
  }

  public void loginToGoogleAccount(String username, String password) {
    clickToLoginField();
    inputTextToLoginField(username);
    clickToNextButton();
    clickToPasswordField();
    inputTextToPasswordField(password);
    clickToNextButton();
  }
}
