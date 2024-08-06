package com.ta.test.challenge;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ta.test.challenge.component.Login;
import com.ta.test.challenge.config.AppDriverConfig;
import com.ta.test.challenge.driver.DriverWrapper;
import com.ta.test.challenge.page.InstallShiftPage;
import com.ta.test.challenge.page.ShiftPage;
import com.ta.test.challenge.util.FileUtils;

@SpringBootTest(properties = {"winappdriver.app=Root"})
@ContextConfiguration(classes = AppDriverConfig.class)
public class InstallationTest {

  private static final String INSTALLATION_SCRIPT = "install_shift.bat";
  private final Logger log = LoggerFactory.getLogger(InstallationTest.class);

  @Value("${shift.username}")
  private String username;
  @Value("${shift.password}")
  private String password;
  @Value("${system.download.path}")
  private File downloadPath;

  @Autowired
  private InstallShiftPage installShiftPage;
  @Autowired
  private Login login;
  @Autowired
  private ShiftPage shiftPage;
  @Autowired
  private DriverWrapper driverWrapper;

  @AfterEach
  public void tearDown() {
    driverWrapper.close();
  }

  @Test
  public void installApplication() throws IOException, InterruptedException {
    File file = FileUtils.firstFileMatching(downloadPath, FileUtils.SHIFT_VERSION_PATTERN);
    Assertions.assertNotNull(file);
    URL url = Thread.currentThread().getContextClassLoader().getResource(INSTALLATION_SCRIPT);
    File batFile = new File(Objects.requireNonNull(url).getPath());
    Process p = Runtime.getRuntime().exec(batFile.getAbsolutePath() + " " + file.getAbsolutePath());
    p.waitFor();
    log.atInfo().log("Batch file executed successfully.");

    installShiftPage.installShift();
    login.loginToShift(username, password);
    Assertions.assertTrue(shiftPage.checkMainAccountDisplayed());
  }
}