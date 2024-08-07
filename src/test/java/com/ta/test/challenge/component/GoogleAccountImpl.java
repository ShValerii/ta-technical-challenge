package com.ta.test.challenge.component;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;
import com.ta.test.challenge.exception.ElementNotFoundException;

import io.qameta.allure.Step;

@Component
public class GoogleAccountImpl implements GoogleAccount {

  private static final String LOGIN_FIELD = "//*[@AutomationId='identifierId']";
  private static final String NEXT_BUTTON = "Next";
  private static final String PASSWORD_FIELD = "Enter your password";
  private static final String CONTINUE_BUTTON = "Continue";
  private static final String SELECT_ALL_FIELD = "//*[@AutomationId='i1']";
  private static final String NEVER_BUTTON = "Never";
  private final DriverWrapper appDriver;

  public GoogleAccountImpl(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  @Override
  public void fullLogin(String username, String password) {
    clickToLoginField();
    inputTextToLoginField(username);
    clickToNextButton();
    clickToPasswordField();
    inputTextToPasswordField(password);
    clickToNextButton();
    clickNeverButton();
    clickContinueButton();
    clickSelectAllField();
    clickContinueButton();
  }

  @Step
  @Override
  public void simpleLogin(String username, String password) {
    clickToLoginField();
    inputTextToLoginField(username);
    clickToNextButton();
    clickToPasswordField();
    inputTextToPasswordField(password);
    clickToNextButton();
    try {
      clickNeverButton();
    } catch (ElementNotFoundException e) {
    }
  }

  @Step
  private void clickToLoginField() {
    appDriver.waitForElement(By.xpath(LOGIN_FIELD)).click();
  }

  @Step
  private void inputTextToLoginField(String text) {
    appDriver.waitForElement(By.xpath(LOGIN_FIELD)).sendKeys(text);
  }

  @Step
  private void clickToNextButton() {
    appDriver.waitForElement(By.name(NEXT_BUTTON)).click();
  }

  @Step
  private void clickToPasswordField() {
    appDriver.waitForElement(By.name(PASSWORD_FIELD)).click();
  }

  @Step
  private void inputTextToPasswordField(String text) {
    appDriver.waitForElement(By.name(PASSWORD_FIELD)).sendKeys(text);
  }

  @Step
  private void clickContinueButton() {
    appDriver.waitForElement(By.name(CONTINUE_BUTTON)).click();
  }

  @Step
  private void clickSelectAllField() {
    appDriver.waitForElement(By.xpath(SELECT_ALL_FIELD)).click();
  }

  @Step
  private void clickNeverButton() {
    appDriver.waitForElement(By.name(NEVER_BUTTON)).click();
  }
}
