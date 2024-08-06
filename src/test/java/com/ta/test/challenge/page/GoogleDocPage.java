package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class GoogleDocPage {

  private static final String RENAME_FIELD = "Rename";
  private static final String DOCUMENT_STATUS = "Document status: Saved to Drive.";
  private static final String CREATE_DOCUMENT_BUTTON = "//MenuItem[@Name='Create new document']";
  private final DriverWrapper appDriver;

  public GoogleDocPage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  public void renameDocument(String name) {
    clickToRenameField();
    inputTextToRenameField(name);
    clickEnterOnRenameField();
    checkDocumentSavedStatus();
  }

  @Step
  public void createNewDocument() {
    try {
      //TODO Require deep investigation. Test without this "sleep" is unstable.
      Thread.sleep(10_000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    appDriver.waitForElement(By.xpath(CREATE_DOCUMENT_BUTTON)).click();
  }

  @Step
  private void clickToRenameField() {
    appDriver.waitForElement(By.name(RENAME_FIELD)).click();
  }

  @Step
  private void inputTextToRenameField(String text) {
    appDriver.waitForElement(By.name(RENAME_FIELD)).sendKeys(text);
  }

  @Step
  private void clickEnterOnRenameField() {
    appDriver.waitForElement(By.name(RENAME_FIELD)).sendKeys(Keys.ENTER);
  }

  @Step
  private void checkDocumentSavedStatus() {
    appDriver.waitForElement(By.name(DOCUMENT_STATUS));
  }
}
