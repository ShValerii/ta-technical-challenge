package com.ta.test.challenge.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ta.test.challenge.utility.DriverFactory;
import com.ta.test.challenge.utility.DriverWrapper;

@Configuration
@ComponentScan(basePackages = {
    "com.ta.test.challenge.component",
    "com.ta.test.challenge.page",
    "com.ta.test.challenge.utility"})
public class AppDriverConfig {

  private final Logger log = LoggerFactory.getLogger(AppDriverConfig.class);
  @Value("${winappdriver.url}")
  private String driverURL;
  @Value("${shift.path}")
  private String appName;
  @Value("${winappdriver.timeout}")
  private long timeout;

  @Bean
  @Primary
  public DriverWrapper appDriver() {
    DriverWrapper appDriver = null;
    try {
      var winDriver = DriverFactory.createWinDriver(appName, driverURL);
      appDriver = new DriverWrapper(winDriver, timeout);
    } catch (Exception e) {
      log.atError().log(e.getMessage());
    }
    return appDriver;
  }
}
