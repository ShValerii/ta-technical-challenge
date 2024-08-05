package com.ta.test.challenge.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class DriverFactory {

  public static WindowsDriver<WebElement> createWinDriver(String appName, String driverURL)
      throws MalformedURLException {
    var capabilities = new DesiredCapabilities();
    capabilities.setCapability("app", appName);
    capabilities.setCapability("automationName", "Windows");
    capabilities.setCapability("ms:waitForAppLaunch", "10");
    var winDriver = new WindowsDriver<>(new URL(driverURL), capabilities);
    winDriver.manage().timeouts().implicitlyWait(2000L, TimeUnit.MILLISECONDS);
    return winDriver;
  }
}
