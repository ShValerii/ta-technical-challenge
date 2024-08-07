package com.ta.test.challenge.component;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class LoginImpl implements Login {

  private static final String SIGN_WITH_GOOGLE_BUTTON = "Sign in with Google";
  private static final String TERMS_OF_USE_FIELD = "//*[@AutomationId='checkbox-accept-terms-checkbox']";
  private final DriverWrapper appDriver;
  private final GoogleAccountImpl googleAccount;

  public LoginImpl(DriverWrapper appDriver, GoogleAccountImpl googleAccount) {
    this.appDriver = appDriver;
    this.googleAccount = googleAccount;
  }

  @Step
  @Override
  public void loginToShift(String username, String password) {
    clickToTermsOfConditionField();
    clickSignWithGoogleButton();
    googleAccount.fullLogin(username, password);
  }

  @Step
  private void clickSignWithGoogleButton() {
    appDriver.waitForElement(By.name(SIGN_WITH_GOOGLE_BUTTON)).click();
  }

  @Step
  private void clickToTermsOfConditionField() {
    appDriver.waitForElement(By.xpath(TERMS_OF_USE_FIELD)).click();
  }
}
