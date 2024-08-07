package com.ta.test.challenge;

import java.nio.file.Path;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.ta.test.challenge.config.ChromeDriverConfig;
import com.ta.test.challenge.driver.DriverWrapper;
import com.ta.test.challenge.util.FileUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

@SpringBootTest(classes = ChromeDriverConfig.class)
public class DownloadTest {

  private static final Pattern pattern = Pattern.compile("^shift-v\\d+.\\d+.\\d+.\\d+.*$");
  private static final String DOWNLOAD_BUTTON = "//div[@class='menu']/a[text()='Download Now']";
  private final Logger log = LoggerFactory.getLogger(DownloadTest.class);

  @Value("${system.download.path}")
  private Path downloadPath;
  @Value("${shift.url}")
  private String shiftUrl;
  @Value("${winappdriver.timeout}")
  private long timeout;

  @Autowired
  private DriverWrapper driverWrapper;

  @AfterEach
  public void tearDown() {
    if (driverWrapper.getDriver() != null) {
      driverWrapper.getDriver().close();
    }
    log.atInfo().log("Driver is closed.");
  }

  @Test
  void downloadShift() throws InterruptedException {
    driverWrapper.getDriver().get(shiftUrl);
    driverWrapper.waitForElement(By.xpath(DOWNLOAD_BUTTON)).click();
    Assertions.assertTrue(verifyFileDownload(), "File don't downloaded.");
    String ver = FileUtils.extractShiftFileVersion(downloadPath.toFile());
    log.atInfo().log("Downloaded version is: " + ver);
    Allure.attachment("version", "Downloaded version is: " + ver);
    Assertions.assertTrue(StringUtils.isNotBlank(ver));
  }

  @Step
  private boolean verifyFileDownload() throws InterruptedException {
    int initFilesSize = FileUtils.filesMatching(downloadPath.toFile(), pattern).length;
    log.atInfo().log("Initial array size is: " + initFilesSize);
    long start = System.currentTimeMillis();
    long end = start + timeout * 1000;
    while (System.currentTimeMillis() < end) {
      int actualFilesSize = FileUtils.filesMatching(downloadPath.toFile(), pattern).length;
      if (actualFilesSize == initFilesSize + 1) { //check that a new file was downloaded
        log.atInfo().log("After download array size is: " + actualFilesSize);
        return true;
      }
      Thread.sleep(1000L);
    }
    return false;
  }
}
