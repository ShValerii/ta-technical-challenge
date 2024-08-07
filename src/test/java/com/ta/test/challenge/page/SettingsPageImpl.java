package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class SettingsPageImpl implements SettingsPage {

  private static final String VERSION_FIELD = "//Text[@Name='You and Shift']/following::*/following::Text";
  private static final String YOU_AND_SHIFT_ITEM = "You and Shift";
  private final DriverWrapper appDriver;

  public SettingsPageImpl(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  @Override
  public String extractVersion() {
    clickToMenuItem(YOU_AND_SHIFT_ITEM);
    return appDriver.waitForElement(By.xpath(VERSION_FIELD)).getText();
  }

  @Step
  private void clickToMenuItem(String name) {
    appDriver.waitForElement(By.name(name)).click();
  }
}
