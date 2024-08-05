package com.ta.test.challenge;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.ta.test.challenge.config.ChromeDriverConfig;
import com.ta.test.challenge.utility.DriverWrapper;
import com.ta.test.challenge.utility.FileUtility;

@SpringBootTest(classes = ChromeDriverConfig.class)
public class DownloadTest {

  private static final String DOWNLOAD_BUTTON = "//div[@class='menu']/a[text()='Download Now']";
  private static final String DOWNLOAD_CHECKER_SCRIPT =
      "return document.querySelector('downloads-manager').shadowRoot"
          + ".querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value";
  private final Logger log = LoggerFactory.getLogger(DownloadTest.class);
  @Autowired
  private WebDriver driver;
  @Value("${shift.name}")
  private String shiftName;
  @Value("${system.download.path}")
  private String downloadPath;
  @Value("${shift.url}")
  private String shiftUrl;
  @Value("${winappdriver.timeout}")
  private long timeout;

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
    driver = null;
  }

  @Test
  void downloadShift() {
    driver.get(shiftUrl);
    new DriverWrapper(driver, timeout).waitForElement(By.xpath(DOWNLOAD_BUTTON)).click();
    checkDownloadProcess();
    //TODO replace with while and fileCheck
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    String ver = FileUtility.extractFileVersion(downloadPath, shiftName);
    log.atInfo().log("Downloaded version is: " + ver);
    Assertions.assertTrue(StringUtils.isNotBlank(ver));
  }

  private void checkDownloadProcess() {
    driver.get("chrome://downloads");
    JavascriptExecutor downloadWindowExecutor = (JavascriptExecutor) driver;
    double percentageProgress = 0;
    while (percentageProgress != 100) {
      percentageProgress = (Long) downloadWindowExecutor.executeScript(DOWNLOAD_CHECKER_SCRIPT);
    }
  }
}
