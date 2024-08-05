package com.ta.test.challenge.component;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class GoogleDocumentComponent {

  private static final String RENAME_FIELD = "Rename";
  private static final String DOCUMENT_STATUS = "Document status: Saved to Drive.";
  private final DriverWrapper appDriver;

  public GoogleDocumentComponent(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  public void clickToResumeField() {
    appDriver.waitForElement(By.name(RENAME_FIELD)).click();
  }

  public void inputTextToResume(String text) {
    appDriver.waitForElement(By.name(RENAME_FIELD)).sendKeys(text);
  }

  public void clickEnterOnResumeField(Keys keys) {
    appDriver.waitForElement(By.name(RENAME_FIELD)).sendKeys(keys);
  }

  public void checkDocumentSavedStatus() {
    appDriver.waitForElement(By.name(DOCUMENT_STATUS));
  }
}
