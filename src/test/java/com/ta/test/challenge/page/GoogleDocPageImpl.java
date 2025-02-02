package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class GoogleDocPageImpl implements GoogleDocPage {

  private static final String RENAME_FIELD = "Rename";
  private static final String RELOAD_BUTTON = "Reload";
  private static final String DOCUMENT_STATUS = "Document status: Saved to Drive.";
  private static final String CREATE_DOCUMENT_BUTTON = "//MenuItem[@Name='Create new document']";
  private final DriverWrapper appDriver;

  public GoogleDocPageImpl(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  @Override
  public void renameDocument(String name) {
    clickToRenameField();
    inputTextToRenameField(name);
    clickEnterOnRenameField();
    checkDocumentSavedStatus();
  }

  @Step
  @Override
  public void createNewDocument() {
    // Without this reload the test is unstable.
    appDriver.waitForElement(By.name(RELOAD_BUTTON)).click();
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
