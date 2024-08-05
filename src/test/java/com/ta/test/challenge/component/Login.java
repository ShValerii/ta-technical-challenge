package com.ta.test.challenge.component;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class Login {

  private static final String SIGN_WITH_GOOGLE_BUTTON = "Sign in with Google";
  private static final String TERMS_OF_USE_FIELD = "//*[@AutomationId='checkbox-accept-terms-checkbox']";
  private final DriverWrapper appDriver;
  private final GoogleAcc googleAcc;

  public Login(DriverWrapper appDriver, GoogleAcc googleAcc) {
    this.appDriver = appDriver;
    this.googleAcc = googleAcc;
  }

  public void clickSignWithGoogleButton(){
    appDriver.waitForElement(By.name(SIGN_WITH_GOOGLE_BUTTON)).click();
  }

  public void clickToTermsOfConditionField(){
    appDriver.waitForElement(By.xpath(TERMS_OF_USE_FIELD)).click();
  }
}
