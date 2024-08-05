package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class MainPage {

  private static final String SETTINGS_BUTTON = "//Button[@Name='Google Drive']/following::*";
  private static final String ADVANCED_SETTINGS_BUTTON = "Advanced Settings";
  private static final String MAIN_ACCOUNT = "tset.acc.lol@gmail.com";
  private static final String ADDITION_ACCOUNT = "My_New_Workplace";
  private static final String ADD_TO_SHIFT_BUTTON = "Add to Shift";
  private static final String ADD_WORKSPACE_BUTTON = "Add Workspace";
  private static final String ADD_APPLICATION_BUTTON = "Add Application";
  private static final String GOOGLE_DOCS_ICON = "Google Docs";
  private static final String TEST_ACCOUNT_DOCS_ICON = "Google Docs - Test_Account";
  private static final String WORKSPACE_NAME_FIELD = "//Edit[@Name='Workspace name']";
  private static final String SAVE_WORKSPACE_BUTTON = "Create Workspace";
  private static final String CREATE_DOCUMENT_BUTTON = "Create new document";
  private static final String GO_BACK_BUTTON = "Go Back";
  private final DriverWrapper appDriver;

  public MainPage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  public void clickToSettingsButton() {
    appDriver.waitForElement(By.xpath(SETTINGS_BUTTON)).click();
  }

  public void clickToAdvancedSettingsButton() {
    appDriver.waitForElement(By.name(ADVANCED_SETTINGS_BUTTON)).click();
  }

  public void clickAddShiftButton() {
    appDriver.waitForElement(By.name(ADD_TO_SHIFT_BUTTON)).click();
  }

  public boolean checkAddToShiftButton() {
    return appDriver.findDisplayed(By.name(ADD_TO_SHIFT_BUTTON)) != null;
  }

  public void clickAddWorkspaceButton() {
    appDriver.waitForElement(By.name(ADD_WORKSPACE_BUTTON)).click();
  }

  public void clickAddApplicationButton() {
    appDriver.waitForElement(By.name(ADD_APPLICATION_BUTTON)).click();
  }

  public boolean checkGoogleDocsDisplayed() {
    return appDriver.findDisplayed(By.name(GOOGLE_DOCS_ICON)).isDisplayed();
  }

  public boolean checkTestAccountDocsDisplayed() {
    return appDriver.findDisplayed(By.name(TEST_ACCOUNT_DOCS_ICON)) != null;
  }

  public void inputTextToWorkspaceNameField(String text) {
    appDriver.waitForElement(By.xpath(WORKSPACE_NAME_FIELD)).sendKeys(text);
  }

  public void clickSaveWorkspaceButton() {
    appDriver.waitForElement(By.name(SAVE_WORKSPACE_BUTTON)).click();
  }

  public boolean checkWorkspaceDisplayed(String name) {
    return appDriver.findDisplayed(By.name(name)).isDisplayed();
  }

  public void clickGoogleDocsIcon() {
    appDriver.waitForElement(By.name(GOOGLE_DOCS_ICON)).click();
  }

  public void clickCreateNewDocument() {
    appDriver.waitForElement(By.name(CREATE_DOCUMENT_BUTTON)).click();
  }

  public void clickGoBackButton() {
    appDriver.waitForElement(By.name(GO_BACK_BUTTON)).click();
  }

  public boolean checkDocumentDisplayed(String name) {
    return appDriver.findDisplayed(By.name(name)) != null;
  }

  public void clickToMainAccount() {
    appDriver.waitForElement(By.name(MAIN_ACCOUNT)).click();
  }

  public void clickToAdditionAccount() {
    appDriver.waitForElement(By.name(ADDITION_ACCOUNT)).click();
  }
}
