# TA-Technical-Challenge
## Overview
This project demonstrates how to run tests using Java and WinAppDriver for Windows desktop application.
### Scope:
<ul>
<li>Test 1: using the Chrome browser, visit https://shift.com/, download Shift browser,
verify download is successful and note version from the file name.</li>
<li>Test 2: Install Shift and login with your test email.</li>
<li>Test 3: Verify the installed version of Shift browser matches the downloaded
version. Confirm the installed version from Settings > Advanced settings > About
Shift.</li>
<li>Test 4: Click the "+" button at the bottom left and add a non-email workspace with a
name. Verify the workspace was added with the proper workspace name.</li>
<li>Test 5: Click the "+" button at the bottom left, add the Google Documents
application, log in with the test email account. Verify the Google documents app.</li>
<li>Test 6: Add a new document and name the title “Test Document”. Verify the
document was created with the title</li>
</ul>

## Ensure you have the following installed:
Java 11 (corretto) [Installation guide](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/windows-install.html) <br>
Gradle 7.6.4 [Installation guide](https://gradle.org/install/) <br>
Chrome  browser [Installation guide](https://www.google.com/intl/en_ca/chrome/dr/download/) <br>
WinAppDriver 1.2.1, Install instructions provided below. <br>


> #### Note: You need to create your own Google test account to use Shift.
> #### Also, you need to update credentials and download path in the src/test/resources/application.yml.
> #### `download.path` should be the same as configured in the System and where Chrome download files by default.
> #### If this path is different from `%USERPROFILE%\Downloads`, please add the full actual path. 
> #### You can check it copy and past to the File Explorer and clicking the Enter button.

## Project Setup
### Clone the repository:
git clone https://github.com/ShValerii/ta-technical-challenge.git
cd ta-technical-challenge

## Install WinAppDriver
1. Download Windows Application Driver installer from <https://github.com/Microsoft/WinAppDriver/releases>
2. Run the installer on a Windows 10 machine where your application under test is installed and will be tested
3. Enable [Developer Mode](https://docs.microsoft.com/en-us/windows/uwp/get-started/enable-your-device-for-development) in Windows settings
4. Run `WinAppDriver.exe` from the installation directory (E.g. `C:\Program Files (x86)\Windows Application Driver`)

Windows Application Driver will then be running on the test machine listening to requests on the default IP address and port (`127.0.0.1:4723`).
`WinAppDriver.exe` can be configured to listen to a different IP address and port as follows:

```
WinAppDriver.exe 4727
WinAppDriver.exe 10.0.0.10 4725
WinAppDriver.exe 10.0.0.10 4723/wd/hub
```

## General Development & Best Practices
[Writing tests guide](https://github.com/microsoft/WinAppDriver/blob/master/Docs/FAQ.md#general-development--best-practices)

To correctly configure WinAppDriver, you need to pass `AppID` to `app` capability. This field is key-sensitive.
If you would like to operate a whole OS, you should use `Root`.
To operate other apps, you should find their `AppID` by running next PowerShell command:
~~~shell
get-StartApps
~~~
And find `AppID` by the application name.

## Run WinAppDriver.exe:
```bash
start "C:\Program Files (x86)\Windows Application Driver\WinAppDriver.exe"
```

## Add Inspect tool for Windows
To add more tests, you'll need to find locators for elements.
Inspect (Inspect.exe) is a Windows-based tool that can select any UI element and view its accessibility data.
You can view both Microsoft UI Automation properties and control patterns and Microsoft Active Accessibility (MSAA) properties.
Inspect can also test the navigational structure of the automation elements in the UI Automation tree and the accessible objects in the Microsoft Active Accessibility hierarchy.

To get it, you need to install Windows SDK.
When the installer will ask you to choose the components, you should choose all components with name starts from "Windows SDK ..."
More details about this tool and Windows SDK, you can find by the links:

[Inspect app description.](https://learn.microsoft.com/en-us/windows/win32/winauto/inspect-objects) <br>
[Windows SDK description and download.](https://developer.microsoft.com/en-us/windows/downloads/windows-sdk/)

## Check ApplicationID names 
To run 

## Run all tests:

> Note: Before run the test you should run the WinAppDriver.

> Note: Before run test, you should be in the Project root directory.
### Run all test
```bash
.\gradlew clean test
```
### Run all test with INFO log level
```bash
.\gradlew clean test -info
```
## Generate Test Report
To generate Allure report, please, run the next command:
```bash
.\gradlew allureReport
```
Files to generate report will be saved to the:
```
build/allure-results
```
> Note: If you want to change this folder, you can edit this path in the next file: allure.properties file.

```
src/test/resources/allure.properties
```

After command done, you can find the Allure report in the next folder:
```
build/reports/allure-report
```
To open it manually, please, open `index.html` file in any internet browser.
Or you can use next command, but after watching you should to close local web server in console 
```bash
.\gradlew allureServe
```

## Open saved Allure report
### Install Allure CLI:
[Install Allure Report for Windows guide](https://allurereport.org/docs/install-for-windows/)

### Open saved report
[How to open saved report guide](https://allurereport.org/docs/gettingstarted-view-report/)

### Save report
To save the Allure report as artifact, you should go to folder, where generated Allure report.
In this project, by-default, it's next folder:
```
build/reports/allure-report
```
Find the last allure-report folder,that contain actual files, rename as suite you, and add it to the .zip archive.


