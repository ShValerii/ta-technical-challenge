package com.ta.test.challenge.utility;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.ta.test.challenge.exception.ElementNotFoundException;

public class DriverWrapper {

  private final WebDriver driver;
  private final long timeout;

  public DriverWrapper(WebDriver driver, long timeout) {
    this.driver = driver;
    this.timeout = timeout;
  }

  public WebElement waitForElement(By by, Duration timeout, Function<WebElement, Boolean> filter) {
    Wait<WebDriver> wait =
        new FluentWait<>(driver)
            .withTimeout(timeout)
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(ElementNotInteractableException.class);
    WebElement[] result = {null};
    try {
      wait.until(d -> {
        try {
          WebElement el = driver.findElement(by);
          boolean res = filter.apply(el);
          if (res) {
            result[0] = el;
          }
          return res;
        } catch (NoSuchElementException ex) {
          return false;
        }
      });
    } catch (TimeoutException e) {
      throw new ElementNotFoundException("Can't find element: " + by.toString(), e);
    }
    return result[0];
  }

  public WebElement waitForElement(By by) {
    return waitForElement(by, Duration.ofSeconds(timeout), Objects::nonNull);
  }

  public WebElement findDisplayed(By by) {
    return waitForElement(by, Duration.ofSeconds(timeout), e -> e != null && e.isDisplayed());
  }
}
