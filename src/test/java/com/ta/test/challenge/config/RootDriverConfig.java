package com.ta.test.challenge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ta.test.challenge.utility.DriverFactory;
import com.ta.test.challenge.utility.DriverWrapper;

@Configuration
@ComponentScan(basePackages = {
    "com.ta.test.challenge.component",
    "com.ta.test.challenge.page",
    "com.ta.test.challenge.utility"})
public class RootDriverConfig {

  private static final String ROOT_APP_NAME = "Root";
  private final Logger log = LoggerFactory.getLogger(RootDriverConfig.class);
  @Value("${winappdriver.url}")
  private String driverURL;
  @Value("${winappdriver.timeout}")
  private long timeout;

  @Bean
  public DriverWrapper rootDriver() {
    DriverWrapper rootDriver = null;
    try {
      var winDriver = DriverFactory.createWinDriver(ROOT_APP_NAME, driverURL);
      rootDriver = new DriverWrapper(winDriver, timeout);
    } catch (Exception e) {
      log.atError().log(e.getMessage());
    }
    return rootDriver;
  }
}
