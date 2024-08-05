package com.ta.test.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.ta.test.challenge.component.GoogleAccComponent;
import com.ta.test.challenge.component.GoogleDocumentComponent;
import com.ta.test.challenge.config.AppDriverConfig;
import com.ta.test.challenge.page.ApplicationStorePage;
import com.ta.test.challenge.page.MainPage;
import com.ta.test.challenge.page.SettingsPage;
import com.ta.test.challenge.utility.FileUtility;

@SpringBootTest
@ContextConfiguration(classes = AppDriverConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class ShiftTest {

  private final Logger log = LoggerFactory.getLogger(ShiftTest.class);
  @Value("${shift.username}")
  private String username;
  @Value("${shift.password}")
  private String password;
  @Value("${shift.name}")
  private String shiftName;
  @Value("${system.download.path}")
  private String downloadPath;

  @Autowired
  private MainPage mainPage;
  @Autowired
  private SettingsPage settingsPage;
  @Autowired
  private ApplicationStorePage applicationStorePage;
  @Autowired
  private GoogleAccComponent googleAccComponent;
  @Autowired
  private GoogleDocumentComponent googleDocumentComponent;

  @Test
  @Order(2)
  public void verifyShiftVersion() {
    log.atInfo().log("verifyShiftVersion");
    String downloadedVer = FileUtility.extractFileVersion(downloadPath, shiftName);
    log.atInfo().log("Downloaded Shift version is: " + downloadedVer);
    mainPage.clickToMainAccount();
    mainPage.clickToSettingsButton();
    mainPage.clickToAdvancedSettingsButton();
    String actualVer = settingsPage.extractVersion();
    log.atInfo().log("Installed Shift version is: " + actualVer);
    Assertions.assertEquals(downloadedVer, actualVer);
  }

  @Test
  @Order(3)
  public void createNewWorkspace() {
    log.atInfo().log(" createNewWorkspace");
    String workspaceName = "MyNewWorkplace";
    mainPage.clickAddShiftButton();
    mainPage.clickAddWorkspaceButton();
    mainPage.inputTextToWorkspaceNameField(workspaceName);
    mainPage.clickSaveWorkspaceButton();
    Assertions.assertTrue(mainPage.checkWorkspaceDisplayed(workspaceName));
  }

  @Test
  @Order(4)
  public void addGoogleDocApp() {
    log.atInfo().log(" addGoogleDocApp");
    mainPage.clickToAdditionAccount();
    mainPage.clickAddShiftButton();
    mainPage.clickAddApplicationButton();
    applicationStorePage.selectSearchField();
    applicationStorePage.inputTextToSearchField("Google Docs");
    applicationStorePage.clickToSearchField();
    mainPage.clickGoogleDocsIcon();
    applicationStorePage.inputTextToAccountNameField("Test_Account");
    applicationStorePage.clickToSaveButton();
    googleAccComponent.clickToLoginField();
    googleAccComponent.inputTextToLoginField(username);
    googleAccComponent.clickToNextButton();
    googleAccComponent.clickToPasswordField();
    googleAccComponent.inputTextToPasswordField(password);
    googleAccComponent.clickToNextButton();
    Assertions.assertTrue(mainPage.checkGoogleDocsDisplayed());
    Assertions.assertTrue(mainPage.checkTestAccountDocsDisplayed());
  }

  @Test
  @Order(5)
  public void createNewGoogleDoc() {
    log.atInfo().log(" createNewGoogleDoc");
    String docTitle = "Test Document";
    mainPage.clickToAdditionAccount();
    mainPage.clickGoogleDocsIcon();
    mainPage.clickCreateNewDocument();
    googleDocumentComponent.clickToResumeField();
    googleDocumentComponent.inputTextToResume(docTitle);
    googleDocumentComponent.clickEnterOnResumeField(Keys.ENTER);
    googleDocumentComponent.checkDocumentSavedStatus();
    mainPage.clickGoBackButton();
    Assertions.assertTrue(mainPage.checkDocumentDisplayed(docTitle));
  }
}
