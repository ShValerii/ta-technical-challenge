package com.ta.test.challenge.page;

public interface ShiftPage {

  void openSettings();

  void createNewWorkspace(String name);

  void openAppStore(String workspace);

  void openApp(String workspace, String appName);

  void goBack();

  boolean checkDocumentDisplayed(String name);

  boolean checkMainAccountDisplayed();

  boolean checkAppIconDisplayed(String appName);

  boolean checkTestAccountDocsDisplayed();

  boolean checkWorkspaceDisplayed(String name);
}
