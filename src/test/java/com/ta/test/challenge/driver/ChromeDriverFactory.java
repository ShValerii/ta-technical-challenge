package com.ta.test.challenge.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class ChromeDriverFactory implements DriverFactory {

  private static final String DOWNLOAD_PROMPT_FOR_DOWNLOAD = "download.prompt_for_download";
  private static final String SAFE_BROWSING_ENABLED = "safebrowsing.enabled";
  private static final String DOWNLOAD_DIRECTORY_UPDATE = "download.directory_upgrade";
  private static final String SETTING_AUTOMATIC_DOWNLOADS_PROMPT =
      "profile.default_content_setting_values.automatic_downloads";
  private static final String PREFS = "prefs";
  private static final String REMOTE_ALLOW_ORIGINS_ARG = "--remote-allow-origins=*";
  private static final String SAFE_BROWSING_DISABLING_ARG = "--safebrowsing-disable-download-protection";
  private static final long IMPLICITLY_WAIT = 2000L;
  private static final long PAGE_LOAD_TIMEOUT = 5000L;
  private final Logger log = LoggerFactory.getLogger(ChromeDriverFactory.class);

  @Override
  public WebDriver createDriver() {
    WebDriverManager.chromedriver().setup();
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put(DOWNLOAD_PROMPT_FOR_DOWNLOAD, false);
    prefs.put(SAFE_BROWSING_ENABLED, true);
    prefs.put(DOWNLOAD_DIRECTORY_UPDATE, true);
    prefs.put(SETTING_AUTOMATIC_DOWNLOADS_PROMPT, 1);

    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption(PREFS, prefs);
    options.addArguments(REMOTE_ALLOW_ORIGINS_ARG);
    options.addArguments(SAFE_BROWSING_DISABLING_ARG);
    WebDriver driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
    log.atInfo().log("Created ChromeDriver.");
    return driver;
  }
}
