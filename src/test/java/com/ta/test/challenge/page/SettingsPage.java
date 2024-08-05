package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class SettingsPage {

  private static final String VERSION_FIELD = "//Text[@Name='You and Shift']/following::*/following::Text";
  private final DriverWrapper appDriver;

  public SettingsPage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  public String extractVersion() {
    return appDriver.waitForElement(By.xpath(VERSION_FIELD)).getText();
  }
}
