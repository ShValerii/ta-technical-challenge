package com.ta.test.challenge.page;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.ta.test.challenge.utility.DriverWrapper;

@Component
public class InstallAppPage {

  private static final String NEXT_BUTTON = "Next";
  private static final String INSTALL_BUTTON = "Install";
  private static final String FINISH_BUTTON = "Finish";
  private static final String INSTALL_ONLY_ME_BUTTON = "Install for me only (recommended)";

  private final DriverWrapper rootDriver;

  public InstallAppPage(DriverWrapper rootDriver) {
    this.rootDriver = rootDriver;
  }

  public void clickInstallOnlyMeButton() {
    rootDriver.waitForElement(By.name(INSTALL_ONLY_ME_BUTTON)).click();
  }

  public void clickNextButton() {
    rootDriver.waitForElement(By.name(NEXT_BUTTON)).click();
  }

  public void clickInstallButton() {
    rootDriver.waitForElement(By.name(INSTALL_BUTTON)).click();
  }

  public void clickFinishButton(){
    rootDriver.waitForElement(By.name(FINISH_BUTTON)).click();
  }
}
