package com.ta.test.challenge;

import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ta.test.challenge.component.GoogleAccount;
import com.ta.test.challenge.config.AppDriverConfig;
import com.ta.test.challenge.driver.DriverWrapper;
import com.ta.test.challenge.page.ApplicationStorePage;
import com.ta.test.challenge.page.GoogleDocPage;
import com.ta.test.challenge.page.SettingsPage;
import com.ta.test.challenge.page.ShiftPage;
import com.ta.test.challenge.util.FileUtils;

@SpringBootTest
@ContextConfiguration(classes = AppDriverConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class ShiftTest {

  private static final String WORKSPACE = "MyNewWorkplace";
  private final Logger log = LoggerFactory.getLogger(ShiftTest.class);

  @Value("${shift.username}")
  private String username;
  @Value("${shift.password}")
  private String password;
  @Value("${system.download.path}")
  private Path downloadPath;

  @Autowired
  private ShiftPage shiftPage;
  @Autowired
  private SettingsPage settingsPage;
  @Autowired
  private ApplicationStorePage applicationStorePage;
  @Autowired
  private GoogleAccount googleAccount;
  @Autowired
  private GoogleDocPage googleDocPage;
  @Autowired
  private DriverWrapper driverWrapper;

  @AfterEach
  public void tearDown() {
    driverWrapper.restart();
  }

  @Test
  @Order(3)
  public void verifyShiftVersion() {
    String downloadedVer = FileUtils.extractShiftFileVersion(downloadPath.toFile());
    log.atInfo().log("Downloaded Shift version is: " + downloadedVer);
    shiftPage.openSettings();
    String actualVer = settingsPage.extractVersion();
    log.atInfo().log("Installed Shift version is: " + actualVer);
    Assertions.assertEquals(downloadedVer, actualVer);
  }

  @Test
  @Order(4)
  public void createNewWorkspace() {
    shiftPage.createNewWorkspace(WORKSPACE);
    log.atInfo().log("Created new workspace: " + WORKSPACE);
    Assertions.assertTrue(shiftPage.checkWorkspaceDisplayed(WORKSPACE));
  }

  @Test
  @Order(5)
  public void addGoogleDocApp() {
    String appName = "Google Docs";
    shiftPage.openAppStore(WORKSPACE);
    applicationStorePage.installApp(appName, "Test_Account");
    log.atInfo().log("Installed " + appName + " application.");
    googleAccount.simpleLogin(username, password);
    Assertions.assertTrue(shiftPage.checkAppIconDisplayed(appName));
    Assertions.assertTrue(shiftPage.checkTestAccountDocsDisplayed());
  }

  @Test
  @Order(6)
  public void createNewGoogleDoc() {
    String docTitle = "Test Document";
    shiftPage.openApp(WORKSPACE, "Google Docs - Test_Account");
    log.atInfo().log("Opened workspace " + WORKSPACE);
    googleDocPage.createNewDocument();
    googleDocPage.renameDocument(docTitle);
    log.atInfo().log("Renamed document to: " + docTitle);
    shiftPage.goBack();
    Assertions.assertTrue(shiftPage.checkDocumentDisplayed(docTitle));
  }
}
