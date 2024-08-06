package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class ShiftPage {

  private static final String SETTINGS_BUTTON = "//Button[@Name='Google Drive']/following::*";
  private static final String ADVANCED_SETTINGS_BUTTON = "Advanced Settings";
  private static final String MAIN_ACCOUNT = "tset.acc.lol@gmail.com";
  private static final String ADD_TO_SHIFT_BUTTON = "Add to Shift";
  private static final String ADD_WORKSPACE_BUTTON = "Add Workspace";
  private static final String ADD_APPLICATION_BUTTON = "Add Application";
  private static final String TEST_ACCOUNT_DOCS_ICON = "Google Docs - Test_Account";
  private static final String WORKSPACE_NAME_FIELD = "//Edit[@Name='Workspace name']";
  private static final String SAVE_WORKSPACE_BUTTON = "Create Workspace";
  private static final String GO_BACK_BUTTON = "Go Back";
  private final DriverWrapper appDriver;

  public ShiftPage(DriverWrapper appDriver) {
    this.appDriver = appDriver;
  }

  @Step
  public void openSettings() {
    clickToMainAccount();
    clickToSettingsButton();
    clickToAdvancedSettingsButton();
  }

  @Step
  public void createNewWorkspace(String name) {
    clickAddShiftButton();
    clickAddWorkspaceButton();
    inputTextToWorkspaceNameField(name);
    clickSaveWorkspaceButton();
  }

  @Step
  public void openAppStore(String workspace) {
    clickToWorkspace(workspace);
    clickAddShiftButton();
    clickAddApplicationButton();
  }

  @Step
  public void openApp(String workspace, String appName) {
    clickToWorkspace(workspace);
    clickAppIcon(appName);
  }

  @Step
  public void goBack() {
    appDriver.waitForElement(By.name(GO_BACK_BUTTON)).click();
  }

  @Step
  public boolean checkDocumentDisplayed(String name) {
    return appDriver.findDisplayed(By.name(name)) != null;
  }

  @Step
  public boolean checkMainAccountDisplayed() {
    return appDriver.findDisplayed(By.name(MAIN_ACCOUNT)) != null;
  }

  @Step
  public boolean checkAppIconDisplayed(String appName) {
    return appDriver.findDisplayed(By.name(appName)) != null;
  }

  @Step
  public boolean checkTestAccountDocsDisplayed() {
    return appDriver.findDisplayed(By.name(TEST_ACCOUNT_DOCS_ICON)) != null;
  }

  @Step
  public boolean checkWorkspaceDisplayed(String name) {
    return appDriver.findDisplayed(By.name(name)) != null;
  }

  @Step
  private void clickToSettingsButton() {
    appDriver.waitForElement(By.xpath(SETTINGS_BUTTON)).click();
  }

  @Step
  private void clickToAdvancedSettingsButton() {
    appDriver.waitForElement(By.name(ADVANCED_SETTINGS_BUTTON)).click();
  }

  @Step
  private void clickAddShiftButton() {
    appDriver.waitForElement(By.name(ADD_TO_SHIFT_BUTTON)).click();
  }

  @Step
  private void clickAddWorkspaceButton() {
    appDriver.waitForElement(By.name(ADD_WORKSPACE_BUTTON)).click();
  }

  @Step
  private void clickAddApplicationButton() {
    appDriver.waitForElement(By.name(ADD_APPLICATION_BUTTON)).click();
  }

  @Step
  private void inputTextToWorkspaceNameField(String text) {
    appDriver.waitForElement(By.xpath(WORKSPACE_NAME_FIELD)).sendKeys(text);
  }

  @Step
  private void clickSaveWorkspaceButton() {
    appDriver.waitForElement(By.name(SAVE_WORKSPACE_BUTTON)).click();
  }

  @Step
  private void clickAppIcon(String appName) {
    appDriver.findDisplayed(By.name(appName)).click();
  }

  @Step
  private void clickToMainAccount() {
    appDriver.waitForElement(By.name(MAIN_ACCOUNT)).click();
  }

  @Step
  private void clickToWorkspace(String name) {
    appDriver.waitForElement(By.name(name)).click();
  }
}
