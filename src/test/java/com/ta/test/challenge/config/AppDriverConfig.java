package com.ta.test.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ta.test.challenge.driver.DriverWrapper;
import com.ta.test.challenge.driver.WinDriverFactory;

@Configuration
@ComponentScan(basePackages = {
    "com.ta.test.challenge.component",
    "com.ta.test.challenge.page",
    "com.ta.test.challenge.driver"})
public class AppDriverConfig {

  @Value("${winappdriver.timeout}")
  private long timeout;

  @Bean
  public DriverWrapper appDriver(WinDriverFactory winDriverFactory) {
    return new DriverWrapper(timeout, winDriverFactory);
  }
}
