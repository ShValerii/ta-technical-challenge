package com.ta.test.challenge;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ta.test.challenge.component.GoogleAcc;
import com.ta.test.challenge.component.Login;
import com.ta.test.challenge.config.RootDriverConfig;
import com.ta.test.challenge.page.InstallAppPage;
import com.ta.test.challenge.page.MainPage;
import com.ta.test.challenge.utility.FileUtility;

@SpringBootTest
@ContextConfiguration(classes = RootDriverConfig.class)
public class InstallationTest {

  private static final Pattern pattern = Pattern.compile("^shift-v\\d+.\\d+.\\d+.\\d+.*$");
  private final Logger log = LoggerFactory.getLogger(InstallationTest.class);

  @Value("${shift.username}")
  private String username;
  @Value("${shift.password}")
  private String password;
  @Value("${shift.install.cmd}")
  private String installCmd;
  @Value("${system.download.path}")
  private File downloadPath;
  @Value("${shift.name.pattern}")
  private Pattern pattern1;

  @Autowired
  private GoogleAcc googleAcc;
  @Autowired
  private InstallAppPage installAppPage;
  @Autowired
  private Login login;
  @Autowired
  private MainPage mainPage;

  @Test
  public void installApplication() {
    try {
      File file = FileUtility.firstFileMatching(downloadPath, pattern);
      Assertions.assertNotNull(file);
      URL url = Thread.currentThread().getContextClassLoader().getResource("install_shift.bat");
      File batFile = new File(Objects.requireNonNull(url).getPath());
      Process p = Runtime.getRuntime().exec(batFile.getAbsolutePath() + " " + file.getAbsolutePath());
      p.waitFor();
      log.atInfo().log("Batch file executed successfully.");
    } catch (IOException e) {
      log.atError().log(e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    installAppPage.clickInstallOnlyMeButton();
    installAppPage.clickNextButton();
    installAppPage.clickInstallButton();
    installAppPage.clickFinishButton();
    login.clickToTermsOfConditionField();
    login.clickSignWithGoogleButton();
    googleAcc.loginToGoogleAccount(username, password);
    googleAcc.clickContinueButton();
    googleAcc.clickSelectAllField();
    googleAcc.clickContinueButton();
    Assertions.assertTrue(mainPage.checkAddToShiftButton());
  }
}