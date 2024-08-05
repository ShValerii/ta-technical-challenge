package com.ta.test.challenge.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;

@Configuration
public class ChromeDriverConfig {

  @Bean
  public WebDriver chromeDriver() {
    WebDriver driver;
    WebDriverManager.chromedriver().setup();
    Map<String, Object> prefs = new HashMap<String, Object>();
    //TODO create constants
    prefs.put("download.prompt_for_download", false);
    prefs.put("safebrowsing.enabled", true);
    prefs.put("download.directory_upgrade", true);
    prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", prefs);
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--safebrowsing-disable-download-protection");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(2000L, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().pageLoadTimeout(5000L, TimeUnit.MILLISECONDS);
    return driver;
  }
}
