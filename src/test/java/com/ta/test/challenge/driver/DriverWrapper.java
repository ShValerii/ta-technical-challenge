package com.ta.test.challenge.driver;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ta.test.challenge.exception.ElementNotFoundException;

public class DriverWrapper {

  private static final String OK_BUTTON = "OK";
  private static final String SHIFT = "Shift";
  private WebDriver driver;
  private final long timeout;
  private final DriverFactory driverFactory;

  public DriverWrapper(long timeout, DriverFactory driverFactory) {
    this.timeout = timeout;
    this.driverFactory = driverFactory;
    this.driver = driverFactory.createDriver();
  }

  public WebElement waitForElement(By by, Duration timeout, Function<WebElement, Boolean> filter) {
    long start = System.currentTimeMillis();
    long end = start + timeout.getSeconds() * 1000;
    while (System.currentTimeMillis() < end) {
      try {
        WebElement el = driver.findElement(by);
        if (filter.apply(el)) {
          return el;
        }
      } catch (NoSuchElementException ex) {
      }
      try {
        Thread.sleep(500L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    throw new ElementNotFoundException("Can't find element: " + by.toString());
  }

  public WebElement waitForElement(By by) {
    return waitForElement(by, Duration.ofSeconds(timeout), Objects::nonNull);
  }

  public WebElement findDisplayed(By by) {
    return waitForElement(by, Duration.ofSeconds(timeout), e -> e != null && e.isDisplayed());
  }

  public WebDriver getDriver() {
    return driver;
  }

  public void close() {
    if (driver != null) {
      driver.findElement(By.name(SHIFT)).sendKeys(Keys.ALT + "" + Keys.F4);
      waitForElement(By.name(OK_BUTTON)).click();
      driver = null;
    }
  }

  public void restart() {
    if (driver != null) {
      driver.findElement(By.name(SHIFT)).sendKeys(Keys.ALT + "" + Keys.F4);
      waitForElement(By.name(OK_BUTTON)).click();
      driver = driverFactory.createDriver();
    }
  }
}
