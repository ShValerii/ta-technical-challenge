package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.driver.DriverWrapper;

import io.qameta.allure.Step;

@Component
public class InstallShiftPageImpl implements InstallShiftPage {

  private static final String NEXT_BUTTON = "Next";
  private static final String INSTALL_BUTTON = "Install";
  private static final String FINISH_BUTTON = "Finish";
  private static final String INSTALL_ONLY_ME_BUTTON = "Install for me only (recommended)";

  private final DriverWrapper rootDriver;

  public InstallShiftPageImpl(DriverWrapper rootDriver) {
    this.rootDriver = rootDriver;
  }

  @Step
  @Override
  public void installShift() {
    clickInstallOnlyMeButton();
    clickNextButton();
    clickInstallButton();
    clickFinishButton();
  }

  @Step
  private void clickInstallOnlyMeButton() {
    rootDriver.waitForElement(By.name(INSTALL_ONLY_ME_BUTTON)).click();
  }

  @Step
  private void clickNextButton() {
    rootDriver.waitForElement(By.name(NEXT_BUTTON)).click();
  }

  @Step
  private void clickInstallButton() {
    rootDriver.waitForElement(By.name(INSTALL_BUTTON)).click();
  }

  @Step
  private void clickFinishButton() {
    rootDriver.waitForElement(By.name(FINISH_BUTTON)).click();
  }
}
