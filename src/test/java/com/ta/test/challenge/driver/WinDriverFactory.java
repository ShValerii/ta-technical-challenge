package com.ta.test.challenge.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.appium.java_client.windows.WindowsDriver;

@Component
public class WinDriverFactory implements DriverFactory {

  private static final String APP = "app";
  private static final String AUTOMATION_NAME = "automationName";
  private static final String WINDOWS = "Windows";
  private static final String WAIT_FOR_APP_LAUNCH = "ms:waitForAppLaunch";
  private static final int LAUNCH_TIMEOUT = 10;
  private static final long WAIT_TIME = 2000L;
  private static final String LOCAL_APP_DATA = "%LocalAppData%";

  @Value("${winappdriver.url}")
  private String driverURL;
  @Value("${winappdriver.app}")
  private String appName;

  @Override
  public WebDriver createDriver() {
    String localAppData = System.getenv("LOCALAPPDATA");
    String appPath = appName.replace(LOCAL_APP_DATA, localAppData);
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(APP, appPath);
    capabilities.setCapability(AUTOMATION_NAME, WINDOWS);
    capabilities.setCapability(WAIT_FOR_APP_LAUNCH, String.valueOf(LAUNCH_TIMEOUT));

    try {
      WindowsDriver<WebElement> winDriver = new WindowsDriver<>(new URL(driverURL), capabilities);
      winDriver.manage().timeouts().implicitlyWait(WAIT_TIME, TimeUnit.MILLISECONDS);
      return winDriver;
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
