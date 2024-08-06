package com.ta.test.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ta.test.challenge.driver.ChromeDriverFactory;
import com.ta.test.challenge.driver.DriverWrapper;

@Configuration
@ComponentScan(basePackages = {
    "com.ta.test.challenge.component",
    "com.ta.test.challenge.page",
    "com.ta.test.challenge.driver"})
public class ChromeDriverConfig {

  @Value("${winappdriver.timeout}")
  private long timeout;

  @Bean
  public DriverWrapper chromeDriver(ChromeDriverFactory chromeDriverFactory) {
    return new DriverWrapper(timeout, chromeDriverFactory);
  }
}
